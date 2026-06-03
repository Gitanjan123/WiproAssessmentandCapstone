

let allSongs      = [];
let filteredSongs = [];

async function loadSongs() {
  showSongsLoading();
  try {
    allSongs = await SongAPI.getAll();
    filteredSongs = [...allSongs];
    renderSongs(filteredSongs);
    updateSongCount(filteredSongs.length);
  } catch (err) {
    showSongsError(err.message);
  }
}

async function searchSongs(query, field = 'q') {
  if (!query.trim()) {
    filteredSongs = [...allSongs];
    renderSongs(filteredSongs);
    updateSongCount(filteredSongs.length);
    return;
  }

  showSongsLoading();
  try {
    const results =
      field === 'q'
        ? await SongAPI.search(query, true)
        : await SongAPI.searchBy(field, query);

    filteredSongs = results;
    renderSongs(results);
    updateSongCount(results.length);
  } catch (err) {
    showSongsError(err.message);
  }
}

function renderSongs(songs) {
  const container = document.getElementById('songsList');
  if (!container) return;

  if (!songs.length) {
    container.innerHTML = `
      <div class="empty-state">
        <div class="empty-icon">🎵</div>
        <h3>No songs found</h3>
        <p>Try a different search term</p>
      </div>`;
    return;
  }

  // Change container to grid layout
  container.style.display = 'grid';
  container.style.gridTemplateColumns = 
    'repeat(auto-fill, minmax(180px, 1fr))';
  container.style.gap = '20px';
  container.style.padding = '8px';

  container.innerHTML = songs.map((song, i) => `
    <div class="song-card fade-in-up"
         style="
           background:var(--surface-2);
           border:1px solid var(--border);
           border-radius:16px;
           padding:16px;
           cursor:pointer;
           transition:all 0.2s ease;
           animation-delay:${i * 0.05}s;
         "
         onclick="openSongDetail(${song.id})"
         data-id="${song.id}"
         onmouseover="this.style.background='var(--surface-3)';
                      this.style.transform='translateY(-4px)';
                      this.style.boxShadow='0 8px 24px rgba(0,0,0,0.3)'"
         onmouseout="this.style.background='var(--surface-2)';
                     this.style.transform='translateY(0)';
                     this.style.boxShadow='none'">

      <!-- Album Art -->
      <div style="
             width:100%;
             aspect-ratio:1;
             border-radius:12px;
             overflow:hidden;
             background:linear-gradient(135deg,#1e1430,#0e1a2e);
             margin-bottom:12px;
             position:relative;
           ">
        ${song.albumArt
          ? `<img src="${song.albumArt}"
                   alt="${escHtml(song.title)}"
                   style="width:100%;height:100%;object-fit:cover;"
                   onerror="this.outerHTML='<div style=width:100%;height:100%;display:flex;align-items:center;justify-content:center;font-size:48px>🎵</div>'">`
          : `<img src="../../assets/images/song-covers/song${(i % 6) + 1}.jpg"
                   alt=""
                   style="width:100%;height:100%;object-fit:cover;"
                   onerror="this.outerHTML='<div style=width:100%;height:100%;display:flex;align-items:center;justify-content:center;font-size:48px>🎵</div>'">`
        }

        <!-- Play button overlay -->
        <div class="play-overlay"
             style="
               position:absolute;
               bottom:8px;
               right:8px;
               width:40px;
               height:40px;
               background:var(--gold);
               border-radius:50%;
               display:flex;
               align-items:center;
               justify-content:center;
               opacity:0;
               transition:opacity 0.2s ease;
               box-shadow:0 4px 12px rgba(0,0,0,0.4);
             "
             onclick="event.stopPropagation();playFromLibrary(${song.id})"
             onmouseover="this.style.opacity='1'"
             onmouseout="this.style.opacity='0'">
          <img src="../../assets/icons/play.png"
               style="width:14px;height:14px;
                      filter:brightness(0) invert(0.1)"
               onerror="this.outerHTML='▶'"/>
        </div>
      </div>

      <!-- Song Info -->
      <div style="overflow:hidden">
        <div style="
               font-size:14px;
               font-weight:500;
               color:var(--text-primary);
               white-space:nowrap;
               overflow:hidden;
               text-overflow:ellipsis;
               margin-bottom:4px;
             ">
          ${escHtml(song.title || song.name)}
        </div>
        <div style="
               font-size:12px;
               color:var(--text-secondary);
               white-space:nowrap;
               overflow:hidden;
               text-overflow:ellipsis;
               margin-bottom:8px;
             ">
          ${escHtml(song.artist || song.singer || '—')}
        </div>
        <div style="
               font-size:11px;
               color:var(--text-muted);
               white-space:nowrap;
               overflow:hidden;
               text-overflow:ellipsis;
             ">
          ${escHtml(song.album || '—')}
        </div>
      </div>

      <!-- Actions -->
      <div style="
             display:flex;
             gap:8px;
             margin-top:10px;
             justify-content:space-between;
             align-items:center;
           "
           onclick="event.stopPropagation()">
        <button class="btn-icon"
                title="Play"
                onclick="playFromLibrary(${song.id})"
                style="flex:1;padding:6px;border-radius:8px;
                       background:var(--surface-3);
                       font-size:12px;">
          ▶ Play
        </button>
        <button class="btn-icon"
                title="Add to playlist"
                onclick="openAddToPlaylistModal(${song.id})"
                style="padding:6px 8px;border-radius:8px;
                       background:var(--surface-3);
                       font-size:14px;">
          ＋
        </button>
      </div>
    </div>`).join('');

  // Show play overlay on card hover
  container.querySelectorAll('.song-card').forEach(card => {
    const overlay = card.querySelector('.play-overlay');
    if (overlay) {
      card.addEventListener('mouseenter', () => {
        overlay.style.opacity = '1';
      });
      card.addEventListener('mouseleave', () => {
        overlay.style.opacity = '0';
      });
    }
  });
}
function updateSongCount(count) {
  const el = document.getElementById('songCount');
  if (el) el.textContent =
    `${count} song${count !== 1 ? 's' : ''}`;
}

function showSongsLoading() {
  const container = document.getElementById('songsList');
  if (!container) return;
  container.innerHTML = `
    <div style="display:flex;justify-content:center;padding:60px">
      <div class="spinner" style="width:32px;height:32px"></div>
    </div>`;
}

function showSongsError(message) {
  const container = document.getElementById('songsList');
  if (!container) return;
  container.innerHTML = `
    <div class="empty-state">
      <div class="empty-icon">⚠️</div>
      <h3>Error loading songs</h3>
      <p>${escHtml(message)}</p>
    </div>`;
}

function openSongDetail(id) {
  window.location.href = `song-detail.html?id=${id}`;
}

async function loadSongDetail() {
  const id = new URLSearchParams(window.location.search).get('id');
  if (!id) { window.history.back(); return; }

  const detailEl = document.getElementById('songDetail');
  detailEl.innerHTML = `
    <div style="display:flex;justify-content:center;padding:60px">
      <div class="spinner" style="width:32px;height:32px"></div>
    </div>`;

  try {
    const song = await SongAPI.getById(id);
    renderSongDetail(song);
  } catch (err) {
    detailEl.innerHTML = `
      <div class="empty-state">
        <div class="empty-icon">⚠️</div>
        <h3>Error loading song</h3>
        <p>${escHtml(err.message)}</p>
      </div>`;
  }
}

function renderSongDetail(song) {
  const el = document.getElementById('songDetail');
  el.innerHTML = `
    <div class="fade-in-up" style="max-width:680px">
      <button onclick="window.history.back()" class="btn-secondary mb-6"
              style="display:inline-flex;align-items:center;gap:6px">
        ← Back to Library
      </button>
      <div class="panel mb-6"
           style="display:flex;gap:24px;align-items:flex-start;flex-wrap:wrap">
        <div style="width:160px;height:160px;border-radius:16px;
                    background:linear-gradient(135deg,#1e1430,#0e1a2e);
                    display:flex;align-items:center;justify-content:center;
                    font-size:64px;flex-shrink:0;overflow:hidden">
          ${song.albumArt
            ? `<img src="${song.albumArt}"
                    style="width:100%;height:100%;object-fit:cover;border-radius:16px">`
            : '🎵'}
        </div>
        <div style="flex:1">
          <div style="font-size:11px;text-transform:uppercase;letter-spacing:0.12em;
                      color:var(--text-muted);margin-bottom:8px">Song</div>
          <h1 class="font-display"
              style="font-size:28px;color:var(--text-primary);margin-bottom:4px">
            ${escHtml(song.title || song.name)}
          </h1>
          <div style="font-size:15px;color:var(--text-secondary);margin-bottom:20px">
            ${escHtml(song.artist || song.singer || '—')}
          </div>
          <div style="display:flex;gap:10px;flex-wrap:wrap">
            <button class="btn-primary"
                    style="padding:10px 24px;border-radius:12px;font-size:14px"
                    onclick="playFromLibrary(${song.id})">▶ Play</button>
            <button class="btn-secondary"
                    onclick="openAddToPlaylistModal(${song.id})">＋ Add to Playlist</button>
          </div>
        </div>
      </div>
      <div class="panel">
        <h2 class="font-display"
            style="font-size:16px;margin-bottom:16px;color:var(--text-secondary)">
          Song Details
        </h2>
        <div style="display:grid;grid-template-columns:1fr 1fr;gap:16px">
          ${detailRow('Album',          song.album)}
          ${detailRow('Music Director', song.musicDirector || song.composer)}
          ${detailRow('Singer',         song.artist || song.singer)}
          ${detailRow('Release Date',
            song.releaseDate
              ? new Date(song.releaseDate).toLocaleDateString('en-IN',
                  { year:'numeric', month:'long', day:'numeric' })
              : null)}
          ${detailRow('Duration', song.duration ? formatDuration(song.duration) : null)}
          ${detailRow('Genre',    song.genre)}
          ${detailRow('Language', song.language)}
          ${detailRow('Visibility',
            song.visible !== false
              ? '<span class="badge badge-success">Public</span>'
              : '<span class="badge badge-danger">Restricted</span>',
            true)}
        </div>
      </div>
    </div>`;
}

function detailRow(label, value, raw = false) {
  if (!value) return '';
  return `
    <div>
      <div style="font-size:11px;text-transform:uppercase;letter-spacing:0.1em;
                  color:var(--text-muted);margin-bottom:4px">${label}</div>
      <div style="font-size:14px;color:var(--text-primary)">
        ${raw ? value : escHtml(String(value))}
      </div>
    </div>`;
}

// ═════════════════════════════════════════════════════════════
// ADD-TO-PLAYLIST MODAL
// ═════════════════════════════════════════════════════════════

let _addToPlaylistSongId = null;

async function openAddToPlaylistModal(songId) {
  _addToPlaylistSongId = songId;
  const modal  = document.getElementById('addToPlaylistModal');
  const listEl = document.getElementById('playlistPickerList');
  if (!modal || !listEl) return;

  modal.classList.add('open');
  listEl.innerHTML = `
    <div style="display:flex;justify-content:center;padding:20px">
      <div class="spinner" style="width:24px;height:24px"></div>
    </div>`;

  try {
    const playlists = await PlaylistAPI.getAll();

    if (!playlists.length) {
      listEl.innerHTML = `
        <div style="text-align:center;padding:24px;color:var(--text-muted);font-size:13px">
          No playlists yet.<br/>
          <a href="playlists.html"
             style="color:var(--gold);margin-top:8px;display:inline-block">
            Create one →
          </a>
        </div>`;
      return;
    }

    listEl.innerHTML = playlists.map(p => `
      <div class="song-row" style="cursor:pointer"
           onclick="addSongToPlaylist(${p.id}, '${escHtml(p.name)}')">
        <div style="font-size:20px">🎵</div>
        <div class="song-info">
          <div class="song-name">${escHtml(p.name)}</div>
          <div class="song-artist">${p.songCount || 0} songs</div>
        </div>
        <div style="color:var(--gold);font-size:18px">＋</div>
      </div>`).join('');
  } catch (err) {
    listEl.innerHTML = `
      <p style="color:#f87171;text-align:center;padding:16px;font-size:13px">
        ${escHtml(err.message)}
      </p>`;
  }
}

function closeAddToPlaylistModal() {
  document.getElementById('addToPlaylistModal')?.classList.remove('open');
  _addToPlaylistSongId = null;
}

async function addSongToPlaylist(playlistId, playlistName) {
  if (!_addToPlaylistSongId) return;
  try {
    await PlaylistAPI.addSong(playlistId, _addToPlaylistSongId);
    closeAddToPlaylistModal();
    showToast(`Added to "${playlistName}"!`, 'success');
  } catch (err) {
    showToast(err.message, 'error');
  }
}