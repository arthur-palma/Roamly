import createGlobalState from "react-create-global-state";

const TOKEN_KEY = "authToken";

const stateInStorage = localStorage.getItem(TOKEN_KEY);
const initialToken = stateInStorage ? stateInStorage : null;

const [_useGlobalAuth, Provider] = createGlobalState(initialToken);

export function useGlobalAuth() {
  const [token, _setToken] = _useGlobalAuth();

  function setToken(newToken) {
    _setToken(newToken);
    localStorage.setItem(TOKEN_KEY, newToken);
  }
  return [token, setToken];
}

export const GlobalAuthProvider = Provider;
