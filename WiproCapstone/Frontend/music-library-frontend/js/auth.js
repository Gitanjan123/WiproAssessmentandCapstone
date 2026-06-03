/**
 * auth.js — Authentication: Login, Register, Logout, Guards, Sidebar
 */

function requireAuth(role = null) {
  const user  = getUser();
  const token = getToken();

  if (!token || !user) {
    const depth = (window.location.pathname.match(/\//g) || []).length - 1;
    const base  = depth <= 1 ? './' : '../../';
    window.location.href = base + 'index.html';
    return false;
  }

  if (role && user.role !== role) {
    if (user.role === 'ADMIN') {
      window.location.href = '../../pages/admin/dashboard.html';
    } else {
      window.location.href = '../../pages/user/dashboard.html';
    }
    return false;
  }

  return true;
}

function redirectIfLoggedIn() {
  const user  = getUser();
  const token = getToken();

  if (token && user) {
    if (user.role === 'ADMIN') {
      window.location.href = 'pages/admin/dashboard.html';
    } else {
      window.location.href = 'pages/user/dashboard.html';
    }
  }
}

function showAlert(elementId, message, type = 'error') {
  const el = document.getElementById(elementId);
  if (!el) return;
  el.textContent = message;
  el.className = `mb-4 p-3 rounded-xl text-sm text-center font-body alert-${type === 'error' ? 'error' : 'success'}`;
  el.classList.remove('hidden');
  setTimeout(() => el.classList.add('hidden'), 5000);
}

function setLoading(btnId, loading, text = '') {
  const btn = document.getElementById(btnId);
  if (!btn) return;
  if (loading) {
    btn.disabled  = true;
    btn.innerHTML = `<span class="spinner" style="width:16px;height:16px;border-width:2px;vertical-align:middle;"></span>`;
  } else {
    btn.disabled    = false;
    btn.textContent = text;
  }
}

async function handleLogin() {
  const email    = document.getElementById('loginEmail')?.value?.trim();
  const password = document.getElementById('loginPassword')?.value;

  if (!email || !password) {
    showAlert('loginAlert', 'Please enter your email and password.', 'error');
    return;
  }

  setLoading('loginBtn', true);
  try {
    const res  = await AuthAPI.login(email, password);
    const user = res.user || res;
    setToken(res.token);
    setUser(user);

    if (user.role === 'ADMIN') {
      window.location.href = 'pages/admin/dashboard.html';
    } else {
      window.location.href = 'pages/user/dashboard.html';
    }
  } catch (err) {
    showAlert('loginAlert', err.message || 'Login failed. Please try again.', 'error');
    setLoading('loginBtn', false, 'Sign In');
  }
}

async function handleRegister() {
  const firstName       = document.getElementById('regFirstName')?.value?.trim();
  const lastName        = document.getElementById('regLastName')?.value?.trim();
  const email           = document.getElementById('regEmail')?.value?.trim();
  const phone           = document.getElementById('regPhone')?.value?.trim();
  const password        = document.getElementById('regPassword')?.value;
  const confirmPassword = document.getElementById('regConfirmPassword')?.value;

  if (!firstName || !lastName || !email || !phone || !password) {
    showAlert('registerAlert', 'All fields are required.', 'error');
    return;
  }
  if (password.length < 8) {
    showAlert('registerAlert', 'Password must be at least 8 characters.', 'error');
    return;
  }
  if (password !== confirmPassword) {
    showAlert('registerAlert', 'Passwords do not match.', 'error');
    return;
  }

  setLoading('registerBtn', true);
  try {
    await AuthAPI.register({ firstName, lastName, email, phone, password, role: 'USER' });
    showAlert('registerAlert', 'Account created! Redirecting to login…', 'success');
    setTimeout(() => { window.location.href = 'index.html'; }, 1500);
  } catch (err) {
    showAlert('registerAlert', err.message || 'Registration failed.', 'error');
    setLoading('registerBtn', false, 'Create Account');
  }
}

async function handleLogout() {
  try { await AuthAPI.logout(); } catch (_) {}
  clearAuth();
  const depth = (window.location.pathname.match(/\//g) || []).length - 1;
  const base  = depth <= 1 ? './' : '../../';
  window.location.href = base + 'index.html';
}


function populateSidebarUser() {
  const user = getUser();
  if (!user) return;

  const initials =
    ((user.firstName?.[0] || '') + (user.lastName?.[0] || '')).toUpperCase() ||
    user.email?.[0]?.toUpperCase() ||
    'U';

  const nameEl  = document.getElementById('sidebarUserName');
  const roleEl  = document.getElementById('sidebarUserRole');
  const textEl  = document.getElementById('sidebarAvatarText'); // Pattern B
  const avatarEl = document.getElementById('sidebarAvatar');    // Pattern A

  if (nameEl)  nameEl.textContent = `${user.firstName || ''} ${user.lastName || ''}`.trim() || user.email;
  if (roleEl)  roleEl.textContent = user.role === 'ADMIN' ? 'Admin' : 'User';

  if (textEl)       textEl.textContent  = initials; // Pattern B — target inner span
  else if (avatarEl) avatarEl.textContent = initials; // Pattern A — set directly
}

document.addEventListener('DOMContentLoaded', () => {
  const path = window.location.pathname;
  const isAuthPage =
    path.endsWith('index.html') ||
    path.endsWith('register.html') ||
    path === '/' ||
    path.endsWith('/');

  if (isAuthPage) redirectIfLoggedIn();

  ['loginEmail', 'loginPassword'].forEach(id => {
    document.getElementById(id)?.addEventListener('keydown', e => {
      if (e.key === 'Enter') handleLogin();
    });
  });

  ['regFirstName','regLastName','regEmail','regPhone','regPassword','regConfirmPassword'].forEach(id => {
    document.getElementById(id)?.addEventListener('keydown', e => {
      if (e.key === 'Enter') handleRegister();
    });
  });
});