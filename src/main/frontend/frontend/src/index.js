import React from 'react';
import ReactDOM from 'react-dom/client';  // 여기 변경!
import AppRouter from './routers/AppRouter';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <AppRouter />
    </React.StrictMode>
);
