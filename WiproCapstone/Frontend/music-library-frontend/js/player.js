const Player = (() => {
  let queue    = [];
  let index    = 0;
  let playing  = false;
  let repeat   = false;
  let shuffle  = false;
  let progress = 0;
  let timer    = null;
  let duration = 180;

  // ── Show/hide player bar ─────────────────────────────────
  function showBar() {
    document.querySelector('.player-bar')?.classList.add('visible');
  }
  function hideBar() {
    document.querySelector('.player-bar')?.classList.remove('visible');
  }

  // ── Update UI ────────────────────────────────────────────
  function updateUI() {
    const song = queue[index];
    if (!song) return;

    setEl('playerSongName',   song.title || song.name || 'Unknown');
    setEl('playerSongArtist', song.artist || song.singer || '');
    setEl('playerThumb',      song.albumArt
      ? `<img src="${song.albumArt}" style="width:100%;height:100%;object-fit:cover;border-radius:8px">`
      : '🎵');

    // Update player bar play button
    const playIcon = playing ? '⏸' : '▶';
    const playBtnBar = document.getElementById('playerPlayBtnBar');
    if (playBtnBar) playBtnBar.innerHTML = playIcon;

    // Update shuffle active state — bar button only
    document.getElementById('playerShuffleBtnBar')
      ?.classList.toggle('active', shuffle);

    // Update repeat active state — bar button only
    document.getElementById('playerRepeatBtnBar')
      ?.classList.toggle('active', repeat);

    // Highlight active song row
    document.querySelectorAll('.song-row').forEach(row => {
      row.classList.toggle('playing',
        String(row.dataset.id) === String(song.id));
    });
  }

  function setEl(id, html) {
    const el = document.getElementById(id);
    if (el) el.innerHTML = html;
  }

  // ── Progress ─────────────────────────────────────────────
  function startProgress() {
    clearInterval(timer);
    const song = queue[index];
    duration = song?.duration || 180;
    let elapsed = 0;
    timer = setInterval(() => {
      if (!playing) return;
      elapsed++;
      progress = Math.min((elapsed / duration) * 100, 100);
      updateProgress(elapsed, duration);
      if (elapsed >= duration) {
        clearInterval(timer);
        handleSongEnd();
      }
    }, 1000);
  }

  function updateProgress(elapsed, total) {
    const fill = document.getElementById('progressFill');
    const cur  = document.getElementById('progressCurrent');
    const tot  = document.getElementById('progressTotal');
    if (fill) fill.style.width = `${(elapsed / total) * 100}%`;
    if (cur)  cur.textContent  = formatTime(elapsed);
    if (tot)  tot.textContent  = formatTime(total);
  }

  function formatTime(s) {
    const m = Math.floor(s / 60);
    return `${m}:${String(s % 60).padStart(2, '0')}`;
  }

  function handleSongEnd() {
    if (repeat) {
      progress = 0;
      play(index);
    } else {
      next();
    }
  }

  // ── Core Controls ────────────────────────────────────────
  function play(i) {
    if (!queue.length) return;
    index = i !== undefined ? i : index;
    playing = true;
    showBar();
    updateUI();
    startProgress();
  }

  function pause() {
    playing = false;
    updateUI();
    clearInterval(timer);
  }

  function togglePlay() {
    if (playing) pause();
    else {
      playing = true;
      updateUI();
      startProgress();
    }
  }

  function next() {
    if (!queue.length) return;
    if (shuffle) {
      let newIdx;
      do {
        newIdx = Math.floor(Math.random() * queue.length);
      } while (newIdx === index && queue.length > 1);
      index = newIdx;
    } else {
      index = (index + 1) % queue.length;
    }
    play(index);
  }

  function prev() {
    if (!queue.length) return;
    index = (index - 1 + queue.length) % queue.length;
    play(index);
  }

  function toggleRepeat() {
    repeat = !repeat;
    updateUI();
  }

  function toggleShuffle() {
    shuffle = !shuffle;
    updateUI();
  }

  function stop() {
    pause();
    progress = 0;
    updateProgress(0, duration);
    hideBar();
  }

  function setVolume(val) {
    const icon = document.getElementById('volumeIcon');
    if (icon) icon.textContent = val == 0 ? '🔇' : val < 50 ? '🔉' : '🔊';
  }

  // ── Seek ─────────────────────────────────────────────────
  function seekTo(percent) {
    const elapsed = Math.floor((percent / 100) * duration);
    progress = percent;
    clearInterval(timer);
    let cur = elapsed;
    timer = setInterval(() => {
      if (!playing) return;
      cur++;
      updateProgress(cur, duration);
      if (cur >= duration) { clearInterval(timer); handleSongEnd(); }
    }, 1000);
    updateProgress(elapsed, duration);
  }

  // ── Load queue ───────────────────────────────────────────
  function loadQueue(songs, startIndex = 0) {
    queue = songs;
    play(startIndex);
  }

  return {
    play, pause, togglePlay, next, prev, stop,
    toggleRepeat, toggleShuffle, setVolume, seekTo, loadQueue,
    get isPlaying() { return playing; }
  };
})();

// ── Global shortcuts ─────────────────────────────────────────
function playerPlay(index) {
  const songs = typeof playlistSongs !== 'undefined' ? playlistSongs : [];
  Player.loadQueue(songs, index);
}

function playFromLibrary(songId) {
  const songs = typeof allSongs !== 'undefined' ? allSongs : [];
  const idx   = songs.findIndex(s => s.id === songId);
  Player.loadQueue(songs, idx >= 0 ? idx : 0);
}

// ── Wire up player bar buttons ────────────────────────────────
function initPlayer() {
  document.getElementById('playerPlayBtnBar')?.addEventListener('click',
    () => Player.togglePlay());
  document.getElementById('playerNextBtn')?.addEventListener('click',
    () => Player.next());
  document.getElementById('playerPrevBtn')?.addEventListener('click',
    () => Player.prev());
  document.getElementById('playerStopBtn')?.addEventListener('click',
    () => Player.stop());
  document.getElementById('playerShuffleBtnBar')?.addEventListener('click',
    () => Player.toggleShuffle());
  document.getElementById('playerRepeatBtnBar')?.addEventListener('click',
    () => Player.toggleRepeat());

  document.getElementById('volumeSlider')?.addEventListener('input',
    e => Player.setVolume(+e.target.value));

  document.getElementById('progressTrack')?.addEventListener('click', e => {
    const rect = e.currentTarget.getBoundingClientRect();
    const pct  = ((e.clientX - rect.left) / rect.width) * 100;
    Player.seekTo(pct);
  });
}

function initPlayerForPlaylist(songs) {
  if (songs.length) Player.loadQueue(songs, 0);
  initPlayer();
}

document.addEventListener('DOMContentLoaded', initPlayer);