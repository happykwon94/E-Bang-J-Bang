<template>
  <div class="login-body container mt-5 mb-5">
    <form class="form-signin">
      <div><h2>회원가입</h2></div>
      <div class="">
        <label for="">아이디</label>
        <input type="text" class="form-control" placeholder="아이디 입력" required autofocus v-model="user.id" @keyup="idValidate" />
        <span :class="{ 'text-primary': isValidateId, 'text-danger': !isValidateId }">{{ idValidateMsg }}</span>
      </div>
      <div id="idResult" class="mt-1"></div>
      <br />
      <div class="">
        <label for="">닉네임</label>
        <input type="text" class="form-control" placeholder="닉네임 입력" required :value="user.nickName" @input="nickNameValidate" />
        <span :class="{ 'text-primary': isValidateNickName, 'text-danger': !isValidateNickName }">{{ nickNameValidateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">비밀번호</label>
        <input type="password" class="form-control form-control1" id="password" name="password" placeholder="비밀번호 입력" autocomplete="off" v-model="user.password" />
      </div>
      <br />
      <div class="">
        <label for="">비밀번호 확인</label>
        <input type="password" class="form-control form-control2" placeholder="비밀번호 확인" v-model="passwordVal" autocomplete="off" @change="pwValidate" />
        <span class="text-danger">{{ passwordValidateMsg }}</span>
      </div>
      <div id="passwordResult" class="mt-1"></div>
      <br />
      <div class="">
        <label for="">연락처</label>
        <input type="tel" class="form-control" id="tel" name="tel" placeholder="연락처 입력" autocomplete="off" v-model="user.phone" />
        <span class="text-danger">{{ phoneValidateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">이메일</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="이메일 입력" autocomplete="off" v-model="user.email" />
        <span class="text-danger">{{ emailValidateMsg }}</span>
      </div>
      <br />
      <div>
        <button type="button" class="btn btn-primary submit-userinfo" @click="join">회원가입</button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { validate } from "@/api/user.js";

const userStore = "userStore";
export default {
  name: "SignUp",
  data() {
    return {
      user: {
        id: "",
        password: "",
        nickName: "",
        phone: "",
        email: null,
      },
      passwordVal: "",
      idValidateMsg: "",
      passwordValidateMsg: "",
      nickNameValidateMsg: "",
      phoneValidateMsg: "",
      emailValidateMsg: "",
      isValidateId: false,
      isValidatePW: false,
      isValidateNickName: false,
      isValidatePhone: false,
      isValidateEmail: false,
    };
  },
  watch: {
    "user.id": function (val) {
      const reg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

      if (reg.exec(val) !== null) {
        return (this.user.id = this.user.id.slice(0, -1));
      }
    },
    "user.phone": function (val) {
      const reg = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

      if (val.length == 0) {
        this.isValidatePhone = false;
        this.phoneValidateMsg = "";
      } else {
        if (reg.test(val) !== true) {
          this.isValidatePhone = false;
          this.phoneValidateMsg = "010-xxxx-xxxx 양식을 확인해주세요.";
        } else {
          this.isValidatePhone = true;
          this.phoneValidateMsg = "";
        }
      }
    },
    "user.email": function (val) {
      const reg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

      if (val.length == 0) {
        this.isValidateEmail = false;
        this.emailValidateMsg = "";
      } else {
        if (reg.test(String(val).toLowerCase()) !== true) {
          this.isValidateEmail = false;
          this.emailValidateMsg = "xxxxx@xxxxxx.xxx 양식을 확인해주세요.";
        } else {
          this.isValidateEmail = true;
          this.emailValidateMsg = "";
        }
      }
    },
  },
  computed: {
    ...mapState(userStore, ["isSignUp"]),
  },
  methods: {
    ...mapActions(userStore, ["userJoin", "idValidation", "nickNameValidation"]),
    async join() {
      if (this.isValidateId && this.isValidatePW && this.isValidateNickName && this.isValidatePhone && this.isValidateEmail) {
        await this.userJoin(this.user);

        if (this.isSignUp) {
          alert("회원가입 되었습니다.");

          this.$router.push({ name: "SignIn" });
        } else {
          alert("회원가입 중 문제가 발생하였습니다.");
        }
      } else {
        alert("입력 정보를 확인해주세요.");
        return;
      }
    },
    async idValidate() {
      if (this.user.id.length < 1) {
        this.idValidateMsg = "";
      } else if (this.user.id.length < 6) {
        this.isValidateId = false;
        this.idValidateMsg = "아이디는 6 글자 이상 12글자 이하만 가능합니다.";
      } else if (this.user.id.length > 12) {
        this.user.id = this.user.id.substring(0, 12);
        this.isValidateId = false;
        this.idValidateMsg = "아이디는 12글자를 초과할 수 없습니다.";
      } else {
        await validate(
          "id",
          this.user.id,
          (response) => {
            // 사용가능한 아이디
            if (response.data === "success") {
              this.isValidateId = true;
              this.idValidateMsg = "사용가능한 아이디입니다.";
            } else {
              this.isValidateId = false;
              this.idValidateMsg = "사용 불가능한 아이디입니다.";
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    async nickNameValidate(e) {
      this.user.nickName = e.target.value;
      if (this.user.nickName.length < 1) {
        this.nickNameValidateMsg = "";
      } else if (this.user.nickName.length < 2) {
        this.isValidateNickName = false;
        this.nickNameValidateMsg = "닉네임은 2 글자 이상 12글자 이하만 가능합니다.";
      } else if (this.user.nickName.length > 12) {
        this.user.nickName = this.user.nickName.substring(0, 12);
        this.isValidateNickName = false;
        this.nickNameValidateMsg = "닉네임 12글자를 초과할 수 없습니다.";
      } else {
        await validate(
          "nickName",
          this.user.nickName,
          (response) => {
            // 사용가능한 아이디
            if (response.data === "success") {
              this.isValidateNickName = true;
              this.nickNameValidateMsg = "사용 가능한 닉네임입니다.";
            } else {
              this.isValidateNickName = false;
              this.nickNameValidateMsg = "사용 불가능한 닉네임입니다.";
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    pwValidate() {
      if (this.passwordVal == 0) {
        this.passwordValidateMsg = "";
      } else {
        if (this.passwordVal != this.user.password) {
          this.isValidatePW = false;
          this.passwordValidateMsg = "비밀번호가 일치하지 않습니다.";
        } else {
          this.isValidatePW = true;
          this.passwordValidateMsg = "";
        }
      }
    },
  },
};
</script>

<style>
.login-body {
  width: 500px;
  padding-top: 50px;
}

.form-signin {
  border: 1px solid rgb(194, 189, 189);
  width: 500px;
  padding: 50px;
  margin: auto;
}

.div-login {
  margin-bottom: 30px;
}

input.form-control1 {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

input.form-control2 {
  border-top-right-radius: 0;
  border-top-left-radius: 0;
  margin-bottom: 10px;
}

.login-d {
  margin-top: 25px;
  margin-bottom: 25px;
}

.checkbox {
  font-size: 0.8rem;
}

.checkbox .nav-find {
  float: right;
}
</style>
