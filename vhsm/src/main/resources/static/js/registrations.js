function checkbeforesubmit() {
    var adminId = document.getElementById("adminId").value;
    var pw1 = document.getElementById("password").value;
    var pw2 = document.getElementById("password2").value;
    var name = document.getElementById("name").value;
    var dept = document.getElementById("department").value;
    var mobile = document.getElementById("mobile").value;
    var email = document.getElementById("email").value;
    var use = document.getElementById("use").checked; /* boolean */

    if (adminId.length == 0) {
        alert("ID가 입력되지 않았습니다.");
        return false;
    }
    else if (pw1.length == 0) {
        alert("비밀번호가 입력되지 않았습니다.");
        return false;
    }
    else if (pw1 != pw2) {
        alert("비밀번호를 다시 확인하세요.");
        return false;
    }
    else if (name.length == 0) {
        alert("이름이 입력되지 않았습니다.");
        return false;
    }
    else if (dept.length == 0) {
        alert("부서명이 입력되지 않았습니다.");
        return false;
    }
    else if (mobile.length == 0) {
        alert("휴대전화 번호가 입력되지 않았습니다.");
        return false;
    }
    else if (email.length == 0) {
        alert("E-mail 주소가 입력되지 않았습니다.");
        return false;
    }
    else if (use == false) {
        alert("계정 사용 여부 체크 바랍니다.");
        return false;
    }
    else {
        return true;
    }
}

function hsmcheckbeforesubmit() {
    var hsmtype = document.getElementById("hsmtype").value;
    var hsmip = document.getElementById("hsmip").value;
    var serverip = document.getElementById("serverip").value;
    var serverport = document.getElementById("serverport").value;
    var use = document.getElementById("use").checked; /* boolean */

    if (hsmtype.length == 0) {
        alert("HSM 유형이 입력되지 않았습니다.");
        return false;
    }
    else if (hsmip.length == 0) {
        alert("HSM IP가 입력되지 않았습니다.");
        return false;
    }
    else if (serverip.length == 0) {
        alert("서버 IP가 입력되지 않았습니다.");
        return false;
    }
    else if (serverport.length == 0) {
        alert("서버 PORT가 입력되지 않았습니다.");
        return false;
    }
    else if (use == false) {
        alert("계정 사용 여부 체크 바랍니다.");
        return false;
    }
    else {
        return true;
    }
}

function diff_pw() {
    alert("동일한 비밀번호를 입력해주세요.");
}

function ifsamepw() {
    var pw1 = document.getElementById('password').value;
    var pw2 = document.getElementById('password2').value;
    var comment = " ";
    if (pw2 != pw1) {
        comment = "동일한 비밀번호를 입력해주세요.";
        document.getElementById('pwcomment').innerHTML = comment;
        return 0;
    }
    else if (pw2 == pw1) {
        comment = " ";
        document.getElementById('pwcomment').innerHTML = comment;
        return 1;
    }
}
