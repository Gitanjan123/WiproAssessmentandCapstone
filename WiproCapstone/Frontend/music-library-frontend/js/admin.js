

let adminSongs = [];

// ── Songs Management ──────────────────────────────────────────
async function loadAdminSongs() {
  const tbody = document.getElementById('adminSongsBody');
  if (!tbody) return;
  tbody.innerHTML = `<tr><td colspan="7"
    style="text-align:center;padding:40px">
    <div class="spinner" style="width:28px;height:28px;margin:auto"></div>
  </td></tr>`;

  try {
    adminSongs = await SongAPI.getAllAdmin(); // ← CHANGED from getAll()
    renderAdminSongs(adminSongs);
    const el = document.getElementById('adminSongCount');
    if (el) el.textContent = `${adminSongs.length} songs`;
  } catch (err) {
    tbody.innerHTML = `<tr><td colspan="7"
      style="text-align:center;padding:40px;color:#f87171">
      ${escHtml(err.message)}
    </td></tr>`;
  }
}

function renderAdminSongs(songs) {
  const tbody = document.getElementById('adminSongsBody');
  if (!tbody) return;

  if (!songs.length) {
    tbody.innerHTML = `<tr><td colspan="7"
      style="text-align:center;padding:40px;color:var(--text-muted)">
      No songs found
    </td></tr>`;
    return;
  }

  tbody.innerHTML = songs.map(s => `
    <tr>
      <td>
        <div style="display:flex;align-items:center;gap:10px">
          <div class="song-thumb" style="width:36px;height:36px;font-size:14px">
            ${s.albumArt ? `<img src="${s.albumArt}" alt="">` : '🎵'}
          </div>
          <div style="font-weight:500;font-size:14px">${escHtml(s.title || s.name)}</div>
        </div>
      </td>
      <td>${escHtml(s.artist || s.singer || '—')}</td>
      <td>${escHtml(s.musicDirector || s.composer || '—')}</td>
      <td>${escHtml(s.album || '—')}</td>
      <td>${s.releaseDate ? new Date(s.releaseDate).toLocaleDateString('en-IN') : '—'}</td>
      <td>
        <label class="toggle"
               title="${s.visible !== false
                 ? 'Visible — click to restrict'
                 : 'Hidden — click to show'}">
          <input type="checkbox" ${s.visible !== false ? 'checked' : ''}
                 onchange="toggleVisibility(${s.id}, this)">
          <span class="toggle-slider"></span>
        </label>
      </td>
      <td>
        <div style="display:flex;gap:6px">
          <button class="btn-icon"
                  onclick="openEditSongModal(${s.id})"
                  title="Edit">✏️</button>
          <button class="btn-icon"
                  onclick="confirmDeleteSong(${s.id},'${escHtml(s.title || s.name)}')"
                  title="Delete" style="color:#f87171">🗑</button>
        </div>
      </td>
    </tr>`).join('');
}

function searchAdminSongs() {
  const q = document.getElementById('adminSongSearch')?.value?.toLowerCase().trim();
  if (!q) { renderAdminSongs(adminSongs); return; }
  const filtered = adminSongs.filter(s =>
    (s.title  || s.name   || '').toLowerCase().includes(q) ||
    (s.artist || s.singer || '').toLowerCase().includes(q) ||
    (s.album  || '').toLowerCase().includes(q)
  );
  renderAdminSongs(filtered);
}

// ── Add Song Modal ────────────────────────────────────────────
function openAddSongModal() {
  document.getElementById('addSongForm')?.reset();
  document.getElementById('addSongAlert')?.classList.add('hidden');
  document.getElementById('addSongModal')?.classList.add('open');
}
function closeAddSongModal() {
  document.getElementById('addSongModal')?.classList.remove('open');
}

async function handleAddSong() {

  const data = {
    title: document.getElementById('songTitle')
      ?.value?.trim(),

    artist: document.getElementById('songArtist')
      ?.value?.trim(),

    musicDirector: document.getElementById('songMusicDirector')
      ?.value?.trim(),

    album: document.getElementById('songAlbum')
      ?.value?.trim(),

    releaseDate: document.getElementById(
      'songReleaseDate'
    )?.value,

    genre: document.getElementById('songGenre')
      ?.value?.trim(),

    duration: parseInt(
      document.getElementById('songDuration')
        ?.value
    ) || null,

    // FIXED: Album image URL added
    albumArt: document.getElementById(
      'songAlbumArt'
    )?.value?.trim(),

    visible: true
  };

  // Validation
  if (!data.title || !data.artist) {
    showAdminAlert(
      'addSongAlert',
      'Title and Artist are required.',
      'error'
    );
    return;
  }

  try {

    console.log('Sending song:', data);

    await SongAPI.create(data);

    closeAddSongModal();

    showToast(
      'Song added to library!',
      'success'
    );

    loadAdminSongs();

  } catch (err) {

    showAdminAlert(
      'addSongAlert',
      err.message,
      'error'
    );

    console.error(err);
  }
}

// ── Edit Song Modal ───────────────────────────────────────────
let editSongId = null;

function openEditSongModal(id) {
  editSongId = id;
  const song = adminSongs.find(s => s.id === id);
  if (!song) return;

  document.getElementById('editSongTitle').value         = song.title         || song.name     || '';
  document.getElementById('editSongArtist').value        = song.artist        || song.singer   || '';
  document.getElementById('editSongMusicDirector').value = song.musicDirector || song.composer || '';
  document.getElementById('editSongAlbum').value         = song.album         || '';
  document.getElementById('editSongReleaseDate').value   = song.releaseDate
    ? song.releaseDate.split('T')[0] : '';
  document.getElementById('editSongGenre').value         = song.genre    || '';
  document.getElementById('editSongDuration').value      = song.duration || '';
  document.getElementById('editSongAlert')?.classList.add('hidden');
  document.getElementById('editSongModal')?.classList.add('open');
}
function closeEditSongModal() {
  document.getElementById('editSongModal')?.classList.remove('open');
}

async function handleEditSong() {
  const data = {
    title:         document.getElementById('editSongTitle')?.value?.trim(),
    artist:        document.getElementById('editSongArtist')?.value?.trim(),
    musicDirector: document.getElementById('editSongMusicDirector')?.value?.trim(),
    album:         document.getElementById('editSongAlbum')?.value?.trim(),
    releaseDate:   document.getElementById('editSongReleaseDate')?.value,
    genre:         document.getElementById('editSongGenre')?.value?.trim(),
    duration:      parseInt(document.getElementById('editSongDuration')?.value) || null,
  };

  if (!data.title) {
    showAdminAlert('editSongAlert', 'Title is required.', 'error');
    return;
  }

  try {
    await SongAPI.update(editSongId, data);
    closeEditSongModal();
    showToast('Song updated!', 'success');
    loadAdminSongs();
  } catch (err) {
    showAdminAlert('editSongAlert', err.message, 'error');
  }
}

// ── Delete Song ───────────────────────────────────────────────
async function confirmDeleteSong(id, name) {
  if (!confirm(`Delete "${name}" from the library? This cannot be undone.`)) return;
  try {
    await SongAPI.delete(id);
    showToast('Song deleted.', 'success');
    loadAdminSongs();
  } catch (err) {
    showToast(err.message, 'error');
  }
}

// ── Toggle Visibility ─────────────────────────────────────────
async function toggleVisibility(id, checkbox) {
  try {
    await SongAPI.toggleVisibility(id);
    const song = adminSongs.find(s => s.id === id);
    if (song) song.visible = checkbox.checked;
    showToast(
      `Song ${checkbox.checked ? 'now visible' : 'now hidden'} for users.`,
      'success'
    );
  } catch (err) {
    checkbox.checked = !checkbox.checked;
    showToast(err.message, 'error');
  }
}

// ── Notifications ─────────────────────────────────────────────
async function loadNotificationHistory() {
  const container = document.getElementById('notificationHistory');
  if (!container) return;
  container.innerHTML = `
    <div style="display:flex;justify-content:center;padding:32px">
      <div class="spinner"></div>
    </div>`;

  try {
    const notifs = await NotificationAPI.getAll();
    if (!notifs.length) {
      container.innerHTML = `
        <div class="empty-state">
          <div class="empty-icon">📭</div>
          <h3>No notifications sent yet</h3>
        </div>`;
      return;
    }
    container.innerHTML = notifs.map(n => `
      <div class="song-row" style="cursor:default">
        <div style="font-size:20px">📧</div>
        <div class="song-info">
          <div class="song-name">${escHtml(n.subject || n.title)}</div>
          <div class="song-artist">
            ${escHtml(n.message || '').substring(0, 80)}
            ${(n.message?.length > 80) ? '…' : ''}
          </div>
        </div>
        <div class="song-meta">
          <div style="font-size:12px">${n.recipientCount || 0} recipients</div>
          <div>${n.sentAt ? new Date(n.sentAt).toLocaleDateString('en-IN') : ''}</div>
        </div>
      </div>`).join('');
  } catch (err) {
    container.innerHTML = `
      <div class="empty-state">
        <p style="color:#f87171">${escHtml(err.message)}</p>
      </div>`;
  }
}

async function handleSendNotification() {
  const subject = document.getElementById('notifSubject')?.value?.trim();
  const message = document.getElementById('notifMessage')?.value?.trim();
  const type    = document.getElementById('notifType')?.value;

  if (!subject || !message) {
    showAdminAlert('notifAlert', 'Subject and message are required.', 'error');
    return;
  }

  const btn = document.getElementById('sendNotifBtn');
  btn.disabled  = true;
  btn.innerHTML = `<span class="spinner"
    style="width:16px;height:16px;border-width:2px;vertical-align:middle"></span> Sending…`;

  try {
    await NotificationAPI.send({ subject, message, type });
    showAdminAlert('notifAlert', 'Notification sent to all users!', 'success');
    document.getElementById('notifSubject').value = '';
    document.getElementById('notifMessage').value = '';
    document.getElementById('previewSubject').textContent = 'Subject will appear here';
    document.getElementById('previewMessage').textContent = 'Message preview…';
    loadNotificationHistory();
  } catch (err) {
    showAdminAlert('notifAlert', err.message, 'error');
  } finally {
    btn.disabled    = false;
    btn.textContent = 'Send to All Users'; // FIX: was "Send Notification"
  }
}

function showAdminAlert(id, msg, type) {
  const el = document.getElementById(id);
  if (!el) return;
  el.textContent = msg;
  el.className   = `p-3 rounded-xl text-sm font-body alert-${type}`;
  el.classList.remove('hidden');
  if (type === 'success') setTimeout(() => el.classList.add('hidden'), 4000);
}