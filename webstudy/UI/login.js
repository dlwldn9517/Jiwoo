document.getElementById('btn_signin').onclick = function(event) {
    var pw = document.getElementById('pw');
    if(pw.value == '') {
        alert('비밀번호를 입력하세요.');
        event.preventDefault();    /* 기본 동작 막기 (서브밋 막기) */
        return;   /* 어쨌든 잘못 됐으니 더이상 아무것도 안하게끔 */
    } else if(pw.value.length < 4) {
        alert('비밀번호가 4글자 이하입니다.');
        event.preventDefault();
        return;  
    }
}
document.getElementById('id').onkeyup = function(event) {    /* onkeyup : 한 글자 쓸 때마다 */
    var id = document.getElementById('id');
    var id_msg = document.getElementById('id_msg');
    if(id.value.length == 0) {
        id_msg.textContent = '';
    } else if(id.value.length < 4) {
        id_msg.textContent = '아이디는 4글자 이상입니다.';
    } else if(id.value.length >= 4) {
        id_msg.textContent = ' 정상적인 아이디입니다.';
    }
}


