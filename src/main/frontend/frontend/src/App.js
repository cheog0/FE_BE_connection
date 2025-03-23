import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';  // useNavigate 임포트

function App() {
    const [userid, setUserid] = useState('');
    const [password, setPassword] = useState('');
    const [responseMessage, setResponseMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();  // navigate 함수 생성

    const handleSubmit = () => {
        const data = { userid, password };

        axios.post('http://localhost:8080/api/login', data)
            .then(response => {
                setResponseMessage("로그인에 성공했습니다.");
                setErrorMessage('');
                navigate('/home');  // 로그인 성공 시 /home으로 리디렉션
            })
            .catch(error => {
                if (error.response && error.response.data) {
                    setErrorMessage(error.response.data);
                } else {
                    setErrorMessage('알 수 없는 오류가 발생했습니다.');
                }
                setResponseMessage('');
                console.log(error);
            });
    };

    return (
        <div>
            <h2>로그인</h2>
            <div>
                <label>아이디:</label>
                <input
                    type="text"
                    value={userid}
                    onChange={(e) => setUserid(e.target.value)}
                />
            </div>
            <div>
                <label>비밀번호:</label>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button onClick={handleSubmit}>로그인</button>

            {/* 로그인 성공 메시지 표시 */}
            {responseMessage && <div style={{ color: 'green', marginTop: '10px' }}>{responseMessage}</div>}

            {/* 에러 메시지 표시 */}
            {errorMessage && <div style={{ color: 'red', marginTop: '10px' }}>{errorMessage}</div>}
        </div>
    );
}

export default App;
