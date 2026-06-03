

// ── Guard for USER pages ──────────────────────────────────────
function guardUser() {
  const token = localStorage.getItem('melodia_token');
  const user  = JSON.parse(localStorage.getItem('melodia_user') || 'null');

  if (!token || !user) {
    window.location.href = '../../index.html';
    return;
  }

  if (user.role === 'ADMIN') {
    window.location.href = '../../pages/admin/dashboard.html';
  }
}

// ── Guard for ADMIN pages ─────────────────────────────────────
function guardAdmin() {
  const token = localStorage.getItem('melodia_token');
  const user  = JSON.parse(localStorage.getItem('melodia_user') || 'null');

  if (!token || !user) {
    window.location.href = '../../index.html';
    return;
  }

  if (user.role !== 'ADMIN') {
    window.location.href = '../../pages/user/dashboard.html';
  }
}

// ── Guard for ROOT-LEVEL admin pages ─────────────────────────
function guardAdminRoot() {
  const token = localStorage.getItem('melodia_token');
  const user  = JSON.parse(localStorage.getItem('melodia_user') || 'null');

  if (!token || !user) {
    window.location.href = './index.html';
    return;
  }

  if (user.role !== 'ADMIN') {
    window.location.href = './pages/user/dashboard.html';
  }
}

// ── Redirect away from login if already logged in ─────────────
function redirectIfLoggedIn() {
  const token = localStorage.getItem('melodia_token');
  const user  = JSON.parse(localStorage.getItem('melodia_user') || 'null');

  if (token && user) {
    if (user.role === 'ADMIN') {
      window.location.href = './pages/admin/dashboard.html';
    } else {
      window.location.href = './pages/user/dashboard.html';
    }
  }
}