<template>
    <div>
        <form action="#">
            <div class="row">
                <div class="col-12 mb-3">
                    <input
                        disabled
                        type="text"
                        class="form-control"
                        placeholder="Your Name"
                        v-model="userName"
                    />
                </div>
                <div class="col-12 mb-3">
                    <input
                        disabled
                        type="email"
                        class="form-control"
                        placeholder="Your Email"
                        v-model="userEmail"
                    />
                </div>
                <div class="col-12 mb-3">
                    <input
                        type="password"
                        class="form-control"
                        placeholder="Password"
                        v-model="userPassword"
                    />
                    <div v-if="pwdTest">
                        Please enter a password of 8 or more in a combination of letters
                        and numbers.
                    </div>
                </div>

                <div class="col-12 mb-3">
                    <input
                        type="password"
                        class="form-control"
                        placeholder="Confirm Password"
                        v-model="userPasswordCheck"
                    />
                    <div v-if="checkPwd">Passwords do not match</div>
                </div>

                <div class="col-12 d-flex justify-content-center">
                    <span class="m-1">
                        <button type="button" class="btn btn-primary" @click="changePwd">
                            Edit
                        </button>
                    </span>
                    <span class="m-1">
                        <button
                            @click="deleteAccount"
                            type="button"
                            class="btn btn-primary"
                        >
                            Delete Account
                        </button>
                    </span>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import http from "@/components/common/axios";

export default {
    components: {},
    data() {
        return {
            userName: sessionStorage.getItem("userName"),
            userEmail: sessionStorage.getItem("userEmail"),
            userPassword: "",
            userPasswordCheck: "",
            checkPwd: false,
            pwdTest: false,
        };
    },
    created() {},
    methods: {
        checkPassword() {
            if (this.userPassword === this.userPasswordCheck) {
                return true;
            } else {
                return false;
            }
        },
        passwordTest() {
            const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            if (!this.userPassword || passwordRegex.test(this.userPassword)) {
                this.pwdTest = false;
            } else {
                this.pwdTest = true;
            }
        },
        async changePwd() {
            if (
                this.userPassword &&
                this.userPasswordCheck &&
                !this.checkPwd &&
                !this.pwdTest
            ) {
                try {
                    let params = {
                        userEmail: this.userEmail,
                        userPassword: this.userPassword,
                    };
                    let response = await http.post("/users/changepwd", params);
                    console.log(response.data);
                    let { data } = response;
                    if (data.result == "success") {
                        this.$alertify.success("비밀번호가 변경되었습니다.");
                        this.$router.push("/");
                    } else {
                        this.$alertify.error("비밀번호가 변경되지 않았습니다.");
                    }
                } catch (error) {
                    console.error(error);
                    this.$alertify.error("비밀번호 변경 과정에서 오류가 발생했습니다.");
                }
            } else {
                this.$alertify.error("변경할 비밀번호를 확인해주세요.");
            }
        },
        async deleteAccount() {
            let userEmail = sessionStorage.getItem("userEmail");
            let response = await http.delete("/users/delete/" + userEmail);
            let { data } = response;
            console.log(data.result);
            if (data.result == "success") {
                this.$alertify.success("계정이 삭제되었습니다.");
                this.$router.push("/");
            } else {
                this.$alertify.error("계정이 삭제 과정에서 오류가 발생했습니다.");
            }
        },
    },
    watch: {
        userPasswordCheck() {
            if (this.userPassword !== "" && !this.checkPassword()) {
                this.checkPwd = true;
            } else {
                this.checkPwd = false;
            }
        },
        userPassword() {
            this.passwordTest();
        },
    },
};
</script>

<style scoped></style>
