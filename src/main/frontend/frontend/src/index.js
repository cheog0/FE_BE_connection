import React from 'react';
import ReactDOM from 'react-dom';
import AppRouter from '../src/routers/AppRouter';  // 라우터 설정을 가져옴

ReactDOM.render(
    <React.StrictMode>
        <AppRouter />  {/* AppRouter로 라우팅을 시작 */}
    </React.StrictMode>,
    document.getElementById('root')
);
