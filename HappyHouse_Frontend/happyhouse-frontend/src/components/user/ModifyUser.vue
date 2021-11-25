<template>
  <div class="login-body container mt-5">
    <form class="form-signin">
      <div><h2>회원 정보</h2></div>
      <div class="">
        <label for="">아이디</label>
        <input type="text" class="form-control" placeholder="아이디 입력" disabled autofocus v-model="this.user.id" />
      </div>
      <br />
      <div class="">
        <label for="">닉네임</label>
        <input type="text" class="form-control" placeholder="닉네임 입력" required :value="aka" @input="nickNameValidate" />
        <span :class="{ 'text-primary': isValidateNickName, 'text-danger': !isValidateNickName }">{{ nickNameValidateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">기존 비밀번호</label>
        <input type="password" class="form-control form-control1" id="password" name="password" placeholder="비밀번호 입력" autocomplete="off" v-model="user.password" @change="validate" />
        <span class="text-danger">{{ validateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">새로운 비밀번호</label>
        <input type="password" class="form-control form-control2" placeholder="새로운 비밀번호" v-model="newPassword" autocomplete="off" @input="setNewPassword" />
      </div>
      <div class="">
        <label for="">새로운 비밀번호 확인</label>
        <input type="password" class="form-control form-control2" placeholder="새로운 비밀번호 확인" v-model="newPasswordVal" autocomplete="off" @input="pwValidate" />
        <span class="text-danger">{{ passwordValidateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">연락처</label>
        <input type="tel" class="form-control" id="tel" name="tel" placeholder="연락처 입력" autocomplete="off" v-model="user.phone" />
        <span class="text-danger">{{ this.phoneValidateMsg }}</span>
      </div>
      <br />
      <div class="">
        <label for="">이메일</label>
        <input type="email" class="form-control" id="email" name="email" disabled placeholder="이메일 입력" autocomplete="off" v-model="this.user.email" />
      </div>
      <br />
      <div>
        <button type="button" class="btn btn-outline-danger submit-userinfo" @click="resignUser">회원 탈퇴</button>
        <button type="button" class="btn btn-outline-primary submit-userinfo" @click="modifyUser">정보 변경</button>
      </div>
    </form>
  </div>
</template>

<script>
import { resignUser, modifyUser, login, validate } from "@/api/user.js";
import { mapState, mapMutations } from "vuex";
const userStore = "userStore";

export default {
  name: "ModifyUser",
  data() {
    return {
      user: {
        id: "",
        password: "",
        nickName: "",
        phone: "",
        email: "",
      },
      aka: "",
      newPassword: "",
      newPasswordVal: "",
      passwordValidateMsg: "",
      isPossible: false,
      isValidate: false,
      validateMsg: "",
      phoneValidateMsg: "",
      isValidateNickName: false,
      nickNameValidateMsg: "",
    };
  },
  watch: {
    "this.user.phone": function (val) {
      if (val === null) {
        this.phoneValidateMsg = "";
      } else {
        const reg = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

        if (val.length == 0) {
          this.phoneValidateMsg = "";
        } else {
          if (reg.test(val) !== true) {
            this.phoneValidateMsg = "010-xxxx-xxxx 양식을 확인해주세요.";
          } else {
            this.phoneValidateMsg = "";
          }
        }
      }
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
    this.user.password = "";
    this.aka = this.userInfo.nickName;
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    resignUser() {
      if (!this.isValidate) {
        alert("기존 비밀번호를 정확하게 입력해주세요.");
        this.user.password = "";
      } else {
        if (confirm("탈퇴를 하면 복구할 수 없습니다. 탈퇴하시겠습니까?")) {
          resignUser(this.user.id, (response) => {
            if (response.data === "success") {
              alert("탈퇴되었습니다.");
              this.SET_IS_LOGIN(false);
              this.SET_USER_INFO(null);
              sessionStorage.removeItem("access-token");
              this.$router.push({ name: "Home" });
            }
          });
        }
      }
    },
    async modifyUser() {
      if (!this.isValidate) {
        alert("기존 비밀번호를 정확하게 입력해주세요.");
        this.user.password = "";
      } else {
        if (this.newPassword.length != 0) {
          if (this.isPossible) {
            this.user.password = this.newPasswordVal;
            this.user.nickName = this.aka;
            await modifyUser(this.user, (response) => {
              if (response.data === "success") {
                alert("회원정보가 수정되었습니다.");
                this.$router.push({ name: "Home" });
              } else {
                alert("회원 정보 수정 시 에러가 발생했습니다.");
              }
            });
          } else {
            alert("새로운 비밀번호가 일치하지 않습니다.");
          }
        } else {
          await modifyUser(this.user, (response) => {
            if (response.data === "success") {
              alert("회원정보가 수정되었습니다.");
              this.$router.push({ name: "Home" });
            } else {
              alert("회원 정보 수정 시 에러가 발생했습니다.");
            }
          });
        }
      }
    },
    setNewPassword(e) {
      this.newPassword = e.target.value;
      console.log(this.newPassword);

      if (this.newPassword.length == 0) {
        this.passwordValidateMsg = "";
      } else {
        if (this.newPassword != this.newPasswordVal) {
          this.isPossible = false;
          this.passwordValidateMsg = "비밀번호가 일치하지 않습니다.";
        } else {
          this.isPossible = true;
          this.passwordValidateMsg = "";
        }
      }
    },
    pwValidate(e) {
      this.newPasswordVal = e.target.value;
      console.log(this.newPasswordVal);

      if (this.newPasswordVal.length == 0) {
        this.passwordValidateMsg = "";
      } else {
        if (this.newPasswordVal != this.newPassword) {
          this.isPossible = false;
          this.passwordValidateMsg = "비밀번호가 일치하지 않습니다.";
        } else {
          this.isPossible = true;
          this.passwordValidateMsg = "";
        }
      }
    },
    async validate() {
      await login(
        this.user,
        (response) => {
          if (response.data.message === "success") {
            this.validateMsg = "";
            this.isValidate = true;
          } else {
            this.validateMsg = "기존 비밀번호를 확인해주세요.";
            this.user.password = "";
            this.isValidate = false;
          }
        },
        (error) => {
          console.log(error);
          this.isValidate = false;
          this.validateMsg = "기존 비밀번호를 확인해주세요.";
          this.user.password = "";
        }
      );
    },
    nickNameValidate(e) {
      this.aka = e.target.value;
      // this.user.nickName = e.target.value;
      if (this.aka < 1) {
        this.nickNameValidateMsg = "";
      } else if (this.aka.length < 2) {
        this.isValidateNickName = false;
        this.nickNameValidateMsg = "닉네임은 2 글자 이상 12글자 이하만 가능합니다.";
      } else if (this.aka.length > 12) {
        this.aka = this.aka.substring(0, 12);
        this.isValidateNickName = false;
        this.nickNameValidateMsg = "닉네임 12글자를 초과할 수 없습니다.";
      } else {
        validate(
          "nickName",
          this.aka,
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

      // this.user.nickName = e.target.value;
      // if (this.user.nickName.length < 1) {
      //   this.nickNameValidateMsg = "";
      // } else if (this.user.nickName.length < 2) {
      //   this.isValidateNickName = false;
      //   this.nickNameValidateMsg = "닉네임은 2 글자 이상 12글자 이하만 가능합니다.";
      // } else if (this.user.nickName.length > 12) {
      //   this.user.nickName = this.user.nickName.substring(0, 12);
      //   this.isValidateNickName = false;
      //   this.nickNameValidateMsg = "닉네임 12글자를 초과할 수 없습니다.";
      // } else {
      //   validate(
      //     "nickName",
      //     this.user.nickName,
      //     (response) => {
      //       // 사용가능한 아이디
      //       if (response.data === "success") {
      //         this.isValidateNickName = true;
      //         this.nickNameValidateMsg = "사용 가능한 닉네임입니다.";
      //       } else {
      //         this.isValidateNickName = false;
      //         this.nickNameValidateMsg = "사용 불가능한 닉네임입니다.";
      //       }
      //     },
      //     (error) => {
      //       console.log(error);
      //     }
      //   );
      // }
    },
  },
};
</script>

<style scoped>
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
