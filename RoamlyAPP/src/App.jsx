import { RouterProvider } from "react-router-dom";
import { GlobalAuthProvider } from "./context/AuthContext";
import Router from "./router";

function App() {
  return (
    <GlobalAuthProvider>
      <RouterProvider router={Router} />
    </GlobalAuthProvider>
  );
}

export default App;
