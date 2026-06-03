
const API_BASE = 'http://localhost:8080/api';

// ── Token Helpers ─────────────────────────────────────────────
const getToken  = () => localStorage.getItem('melodia_token');
const setToken  = (t) => localStorage.setItem('melodia_token', t);
const getUser   = () => JSON.parse(localStorage.getItem('melodia_user') || 'null');
const setUser   = (u) => localStorage.setItem('melodia_user', JSON.stringify(u));
const clearAuth = () => {
  localStorage.removeItem('melodia_token');
  localStorage.removeItem('melodia_user');
};

// ── Base Request ──────────────────────────────────────────────
async function request(method, path, body = null, auth = true) {
  const headers = { 'Content-Type': 'application/json' };
  if (auth) {
    const token = getToken();
    if (token) headers['Authorization'] = `Bearer ${token}`;
  }

  const opts = { method, headers };
  if (body) opts.body = JSON.stringify(body);

  try {
    const res = await fetch(`${API_BASE}${path}`, opts);

    if (res.status === 401) {
      clearAuth();
      const depth = (window.location.pathname.match(/\//g) || []).length - 1;
      const base  = depth <= 1 ? './' : '../../';
      window.location.href = base + 'index.html';
      return;
    }

    const data = await res.json().catch(() => ({}));

    if (!res.ok) {
      throw { status: res.status, message: data.message || data.error || 'Something went wrong' };
    }

    return data;
  } catch (err) {
    if (err.status) throw err;
    throw { status: 0, message: 'Network error — is the server running?' };
  }
}

const GET    = (path, auth = true)       => request('GET',    path, null, auth);
const POST   = (path, body, auth = true) => request('POST',   path, body, auth);
const PUT    = (path, body, auth = true) => request('PUT',    path, body, auth);
const DELETE = (path, auth = true)       => request('DELETE', path, null, auth);

// ── Auth APIs ─────────────────────────────────────────────────
const AuthAPI = {
  login:    (email, password) => POST('/auth/login',    { email, password }, false),
  register: (data)            => POST('/auth/register', data,                false),
  logout:   ()                => POST('/auth/logout'),
};

// ── Song APIs ─────────────────────────────────────────────────
const SongAPI = {

  getAll: () =>
    GET('/songs'),

  getAllAdmin: () =>
    GET('/admin/songs'),

  getById: (id) =>
    GET(`/songs/${id}`),

  search: (query, isAdmin = false) =>
    GET(
      isAdmin
        ? `/admin/songs/search?q=${encodeURIComponent(query)}`
        : `/songs/search?q=${encodeURIComponent(query)}`
    ),
    searchBy: (field, query) =>
    GET(`/songs/search?${field}=${encodeURIComponent(query)}`),

  create: (data) =>
    POST('/admin/songs', data),

  update: (id, data) =>
    PUT(`/admin/songs/${id}`, data),

  delete: (id) =>
    DELETE(`/admin/songs/${id}`),

  toggleVisibility: (id) =>
    PUT(`/admin/songs/${id}/visibility`)
};

// ── Playlist APIs ─────────────────────────────────────────────
const PlaylistAPI = {

  getAll: () => {
    const user = getUser();
    return GET(`/playlists?userId=${user.id}`);
  },

  getById: (id) => {
    const user = getUser();
    return GET(`/playlists/${id}?userId=${user.id}`);
  },

  create: (data) => {
    const user = getUser();
    return POST(`/playlists?userId=${user.id}`, data);
  },

  update: (id, data) => {
    const user = getUser();
    return PUT(`/playlists/${id}?userId=${user.id}`, data);
  },

  delete: (id) => {
    const user = getUser();
    return DELETE(`/playlists/${id}?userId=${user.id}`);
  },

  addSong: (pid, songId) => {
    const user = getUser();
    return POST(
      `/playlists/${pid}/songs?userId=${user.id}`,
      { songId }
    );
  },

  removeSong: (pid, songId) => {
    const user = getUser();
    return DELETE(
      `/playlists/${pid}/songs/${songId}?userId=${user.id}`
    );
  },

  searchSongs: (pid, q) => {
    const user = getUser();
    return GET(
      `/playlists/${pid}/songs/search?q=${encodeURIComponent(q)}&userId=${user.id}`
    );
  },
};
// ── User APIs ─────────────────────────────────────────────────
const UserAPI = {
  getAll:  ()         => GET('/admin/users'),
  getById: (id)       => GET(`/users/${id}`),
  update:  (id, data) => PUT(`/users/${id}`, data),
  delete:  (id)       => DELETE(`/admin/users/${id}`),
};


// ── Admin APIs ─────────────────────────────────────────────────
const AdminAPI = {
  getAll:  ()         => GET('/admin'),
  getById: (id)       => GET(`/admin/${id}`),
  create:  (data)     => POST('/admin', data),
  update:  (id, data) => PUT(`/admin/${id}`, data),
  delete:  (id)       => DELETE(`/admin/${id}`),
};
// ── Notification APIs ─────────────────────────────────────────
const NotificationAPI = {
  send: (data) =>
    POST('/admin/notifications/send', data),

  getAll: () =>
    GET('/admin/notifications'),
};

// GLOBAL UTILITIES — used by songs.js, admin.js, playlist.js

function escHtml(str) {
  if (str === null || str === undefined) return '';
  return String(str)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#39;');
}

function formatDuration(seconds) {
  if (!seconds || isNaN(seconds)) return '';
  const s = Math.floor(seconds);
  const m = Math.floor(s / 60);
  return `${m}:${String(s % 60).padStart(2, '0')}`;
}

function showToast(message, type = 'success') {
  document.getElementById('_melodia_toast')?.remove();

  const colors = {
    success: 'background:#16a34a',
    error:   'background:#dc2626',
    info:    'background:#2563eb',
  };

  const toast = document.createElement('div');
  toast.id = '_melodia_toast';
  toast.textContent = message;
  toast.setAttribute('style', `
    position:fixed;
    bottom:100px;
    left:50%;
    transform:translateX(-50%);
    ${colors[type] || colors.success};
    color:#fff;
    padding:10px 20px;
    border-radius:12px;
    font-size:13px;
    font-family:'DM Sans',sans-serif;
    box-shadow:0 4px 20px rgba(0,0,0,0.4);
    z-index:9999;
    white-space:nowrap;
    pointer-events:none;
    opacity:1;
    transition:opacity 0.4s ease;
  `);

  document.body.appendChild(toast);
  setTimeout(() => {
    toast.style.opacity = '0';
    setTimeout(() => toast.remove(), 400);
  }, 3000);
}