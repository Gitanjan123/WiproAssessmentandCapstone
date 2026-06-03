

let allPlaylists = [];

// ── Load all playlists ───────────────────────────────────────
async function loadPlaylists() {
  const container = document.getElementById('playlistsGrid');
  if (!container) return;
  container.innerHTML = `<div class="flex justify-center py-20" style="grid-column:1/-1"><div class="spinner" style="width:32px;height:32px"></div></div>`;

  try {
    allPlaylists = await PlaylistAPI.getAll();
    renderPlaylists(allPlaylists);
    const countEl = document.getElementById('playlistCount');
    if (countEl) countEl.textContent = `${allPlaylists.length} playlist${allPlaylists.length !== 1 ? 's' : ''}`;
  } catch (err) {
    container.innerHTML = `<div class="empty-state" style="grid-column:1/-1"><div class="empty-icon">⚠️</div><h3>Error</h3><p>${escHtml(err.message)}</p></div>`;
  }
}

function renderPlaylists(playlists) {
  const container = document.getElementById('playlistsGrid');
  if (!container) return;

  if (!playlists.length) {
    container.innerHTML = `
      <div class="empty-state" style="grid-column:1/-1">
        <div class="empty-icon">🎵</div>
        <h3>No playlists yet</h3>
        <p>Create your first playlist to get started</p>
        <button class="btn-primary mt-4" style="padding:10px 24px;border-radius:12px;font-size:14px" onclick="openCreatePlaylistModal()">
          + Create Playlist
        </button>
      </div>`;
    return;
  }

  const emojis = ['🎵','🎸','🎹','🥁','🎷','🎺','🎻','🎙️'];
  container.innerHTML = playlists.map((p, i) => `
    <div class="playlist-card fade-in-up" style="animation-delay:${i*0.05}s">
      <div class="playlist-cover" onclick="openPlaylistDetail(${p.id})" style="cursor:pointer">
        ${emojis[i % emojis.length]}
      </div>
      <div class="playlist-info">
        <div class="playlist-name">${escHtml(p.name)}</div>
        <div class="playlist-count">${p.songCount || 0} songs</div>
        <div style="display:flex;gap:8px;margin-top:12px">
          <button class="btn-secondary" style="flex:1;text-align:center;padding:7px" onclick="openPlaylistDetail(${p.id})">Open</button>
          <button class="btn-icon" onclick="openEditPlaylistModal(${p.id},'${escHtml(p.name)}')" title="Edit">✏️</button>
          <button class="btn-icon btn-danger-icon" onclick="confirmDeletePlaylist(${p.id},'${escHtml(p.name)}')" title="Delete" style="border-color:rgba(239,68,68,0.2);color:#f87171">🗑</button>
        </div>
      </div>
    </div>`).join('');
}

// ── Open Playlist Detail ─────────────────────────────────────
function openPlaylistDetail(id) {
  window.location.href = `playlist-detail.html?id=${id}`;
}

// ── Create Playlist Modal ────────────────────────────────────
function openCreatePlaylistModal() {
  document.getElementById('createPlaylistName').value = '';
  document.getElementById('createPlaylistDesc').value = '';
  document.getElementById('playlistFormAlert').classList.add('hidden');
  document.getElementById('createPlaylistModal').classList.add('open');
}
function closeCreatePlaylistModal() {
  document.getElementById('createPlaylistModal').classList.remove('open');
}

async function handleCreatePlaylist() {
  const name = document.getElementById('createPlaylistName')?.value?.trim();
  const desc = document.getElementById('createPlaylistDesc')?.value?.trim();
  if (!name) {
    showPlaylistFormAlert('Playlist name is required.', 'error'); return;
  }
  try {
    await PlaylistAPI.create({ name, description: desc });
    closeCreatePlaylistModal();
    showToast('Playlist created!', 'success');
    loadPlaylists();
  } catch (err) {
    showPlaylistFormAlert(err.message, 'error');
  }
}

// ── Edit Playlist Modal ──────────────────────────────────────
let editingPlaylistId = null;
function openEditPlaylistModal(id, name) {
  editingPlaylistId = id;
  document.getElementById('editPlaylistName').value = name;
  document.getElementById('editPlaylistModal').classList.add('open');
}
function closeEditPlaylistModal() {
  document.getElementById('editPlaylistModal').classList.remove('open');
}

async function handleEditPlaylist() {
  const name = document.getElementById('editPlaylistName')?.value?.trim();
  if (!name) return;
  try {
    await PlaylistAPI.update(editingPlaylistId, { name });
    closeEditPlaylistModal();
    showToast('Playlist updated!', 'success');
    loadPlaylists();
  } catch (err) {
    showToast(err.message, 'error');
  }
}

// ── Delete Playlist ──────────────────────────────────────────
async function confirmDeletePlaylist(id, name) {
  if (!confirm(`Delete playlist "${name}"? This cannot be undone.`)) return;
  try {
    await PlaylistAPI.delete(id);
    showToast('Playlist deleted.', 'success');
    loadPlaylists();
  } catch (err) {
    showToast(err.message, 'error');
  }
}

// ════════════════════════════════════════════════════════════
// PLAYLIST DETAIL PAGE
// ════════════════════════════════════════════════════════════

let currentPlaylist = null;
let playlistSongs = [];

async function loadPlaylistDetail() {
  const id = new URLSearchParams(window.location.search).get('id');
  if (!id) { window.history.back(); return; }

  try {
    currentPlaylist = await PlaylistAPI.getById(id);
    playlistSongs   = currentPlaylist.songs || [];

    // Set header
    document.getElementById('playlistDetailName').textContent = currentPlaylist.name;
    document.getElementById('playlistDetailCount').textContent = `${playlistSongs.length} songs`;

    renderPlaylistSongs(playlistSongs);
    initPlayerForPlaylist(playlistSongs);
  } catch (err) {
    document.getElementById('playlistSongsList').innerHTML =
      `<div class="empty-state"><div class="empty-icon">⚠️</div><h3>Error</h3><p>${escHtml(err.message)}</p></div>`;
  }
}

function renderPlaylistSongs(songs) {
  const container = document.getElementById('playlistSongsList');
  if (!container) return;

  if (!songs.length) {
    container.innerHTML = `
      <div class="empty-state">
        <div class="empty-icon">🎵</div>
        <h3>This playlist is empty</h3>
        <p>Go to the library and add some songs</p>
      </div>`;
    return;
  }

  container.innerHTML = songs.map((song, i) => `
    <div class="song-row" data-id="${song.id}" data-index="${i}">
      <div class="song-num">${i + 1}</div>
      <div class="song-thumb">${song.albumArt ? `<img src="${song.albumArt}" alt="">` : '🎵'}</div>
      <div class="song-info">
        <div class="song-name">${escHtml(song.title || song.name)}</div>
        <div class="song-artist">${escHtml(song.artist || song.singer || '—')}</div>
      </div>
      <div class="song-meta">
        <div style="font-size:12px;color:var(--text-secondary)">${escHtml(song.album || '—')}</div>
        <div>${song.duration ? formatDuration(song.duration) : ''}</div>
      </div>
      <div class="song-actions" onclick="event.stopPropagation()">
        <button class="btn-icon" onclick="playerPlay(${i})" title="Play">▶</button>
        <button class="btn-icon" onclick="removeFromPlaylist(${currentPlaylist?.id}, ${song.id})" title="Remove" style="color:#f87171">✕</button>
      </div>
    </div>`).join('');
}

async function searchPlaylistSongs() {
  const q = document.getElementById('playlistSearchInput')?.value?.trim();
  if (!currentPlaylist) return;
  if (!q) { renderPlaylistSongs(playlistSongs); return; }

  try {
    const results = await PlaylistAPI.searchSongs(currentPlaylist.id, q);
    renderPlaylistSongs(results);
  } catch (err) {
    showToast(err.message, 'error');
  }
}

async function removeFromPlaylist(playlistId, songId) {
  if (!confirm('Remove this song from the playlist?')) return;
  try {
    await PlaylistAPI.removeSong(playlistId, songId);
    playlistSongs = playlistSongs.filter(s => s.id !== songId);
    renderPlaylistSongs(playlistSongs);
    document.getElementById('playlistDetailCount').textContent = `${playlistSongs.length} songs`;
    showToast('Song removed.', 'success');
  } catch (err) {
    showToast(err.message, 'error');
  }
}

// ── Helpers ───────────────────────────────────────────────────
function showPlaylistFormAlert(msg, type) {
  const el = document.getElementById('playlistFormAlert');
  if (!el) return;
  el.textContent = msg;
  el.className = `mb-4 p-3 rounded-xl text-sm font-body alert-${type}`;
  el.classList.remove('hidden');
}
