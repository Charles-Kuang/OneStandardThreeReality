<template>
    <div class="app-container">
        <el-form>
            <el-form-item>
                <el-button size="medium" @click="toInfo">我的信息</el-button>
            </el-form-item>
            <el-form-item>
                <el-button size="medium" @click.native="logout">退出登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: "Setting",

    data() {
        return {
            user: {}
        }
    },
    created() {
        this.getParams();
    },
    methods: {
        getParams() {
            this.user = this.$route.query.user;
        },

        toInfo() {
            this.$router.push ({
                path: '/person/profile'
            })
        },

        async logout() {
            this.$confirm('确定注销并退出系统吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('LogOut').then(() => {
                location.href = '/index';
                })
            }).catch(() => {});
        }
    }
}

</script>