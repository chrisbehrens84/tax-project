import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import '@trussworks/react-uswds/lib/index.css';
import { Provider } from 'react-redux';
import store from './store.ts';

import "./i18n";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
)
