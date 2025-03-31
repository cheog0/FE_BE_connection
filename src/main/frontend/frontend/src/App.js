import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function App() {
    const [userid, setUserid] = useState('');
    const [password, setPassword] = useState('');
    const [responseMessage, setResponseMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();  // navigate 함수 생성

    const handleSubmit = () => {
        const data = { userid, password };

        // 로그인 API 호출
        axios.post('http://localhost:8080/api/login', data)
            .then(response => {
                // 로그인 성공 시 처리
                setResponseMessage("로그인에 성공했습니다.");
                setErrorMessage('');
                navigate('/home');  // 로그인 성공 시 /home으로 리디렉션
            })
            .catch(error => {
                // 에러 발생 시 처리
                if (error.response && error.response.data) {
                    setErrorMessage(error.response.data);  // 서버에서 반환된 오류 메시지
                } else {
                    setErrorMessage('알 수 없는 오류가 발생했습니다.');  // 서버와의 연결 오류 등
                }
                setResponseMessage('');  // 성공 메시지 초기화
                console.log(error);  // 에러를 콘솔에 출력
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
