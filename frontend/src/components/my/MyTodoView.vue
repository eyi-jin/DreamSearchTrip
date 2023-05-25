<template>
    <div class="section">
        <div class="container">
            <div class="row">
                <div class="col-12" data-aos="fade-up" data-aos-delay="0">
                    <h2 class="heading mb-5">My Todo</h2>
                </div>
            </div>
            <div class="container mt-3">
                <div class="input-group mb-3">
                    <input
                        id="inputSearchWord"
                        type="text"
                        class="form-control"
                        placeholder="Todo"
                        v-model="todoItem"
                    />
                    <button @click="todoListAdd" class="btn btn-primary" type="button">
                        Todo
                    </button>
                </div>
                <div class="mt-5">
                    <div
                        class="justify-content-between d-flex"
                        v-for="(item, index) in this.todoItems"
                        :key="index"
                    >
                        <div class="form-check">
                            <input
                                v-model="item.todoStatus"
                                class="form-check-input me-4"
                                type="checkbox"
                                id="flexCheckDefault"
                                @click="todoCheck(item.todoStatus, item.todoId)"
                            />
                            <label
                                style="zoom: 1.5"
                                class="form-check-label"
                                for="flexCheckDefault"
                                >{{ item.todoContent }}
                            </label>
                        </div>
                        <div @click="todoDelete(item.todoId)">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="2em"
                                height="2em"
                                fill="currentColor"
                                class="bi bi-trash-fill"
                                viewBox="0 0 16 16"
                                color="#f38d61"
                            >
                                <path
                                    d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"
                                />
                            </svg>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import http from "@/components/common/axios";
export default {
    data() {
        return {
            todoItem: "",
            todoItems: [],
            selectedTodo: [],
        };
    },
    mounted() {},
    created() {
        if (sessionStorage.getItem("userName")) {
            console.log("here");

            this.todoList();
        } else {
            this.$router.push("/login");
        }
    },
    methods: {
        async todoListAdd() {
            let params = {
                todoContent: this.todoItem,
                todoStatus: 0,
            };
            let response = await http.post("/todo/todoRegist", params);
            let { data } = response;
            if (data.result == "success") {
                this.$alertify.success("할일이 등록되었습니다.");
            } else {
                this.$alertify.error("등록과정에서 오류가 발생했습니다.");
            }
            this.todoItem = "";
            this.todoList();
        },
        async todoList() {
            let response = await http.get("/todo/todoView");
            console.log(response);
            let { data } = response;
            console.log(data);
            this.todoItems = data;
            this.todoItems.forEach((data) => {
                if (data.todoStatus == 1) {
                    this.selectedTodo.push(data.todoId);
                }
            });
        },
        async todoCheck(status, todoId) {
            console.log(status);
            if (status == 0) {
                status = 1;
            } else {
                status = 0;
            }
            let params = {
                todoId: todoId,
                todoStatus: status,
            };
            let response = await http.put("/todo/todoUpdate", params);
            let { data } = response;
            if (data.result !== "success") {
                this.$alertify.error("체크과정에서 오류가 발생했습니다.");
            }
            this.todoList();
        },
        async todoDelete(e) {
            let response = await http.delete("/todo/todoDelete/" + e);
            let { data } = response;
            if (data.result == "success") {
                this.$alertify.success("할일이 삭제되었습니다.");
            } else {
                this.$alertify.error("삭제과정에서 오류가 발생했습니다.");
            }
            this.todoList();
        },
    },
};
</script>

<style scoped></style>
