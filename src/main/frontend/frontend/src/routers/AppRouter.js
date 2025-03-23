import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import App from '../App';  // 로그인 페이지
import Home from '../pages/Home';  // 홈 페이지

function AppRouter() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<App />} />  {/* 로그인 페이지 */}
                <Route path="/home" element={<Home />} />  {/* 홈 페이지 */}
            </Routes>
        </Router>
    );
}

export default AppRouter;
