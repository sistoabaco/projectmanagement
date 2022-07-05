<template>
 <div>
   <h2>E-mail</h2>
       <input type="text" placeholder="email address" v-model="username"/>
<!--        {{email}}-->

       <h2>Password</h2>
       <input type="password" placeholder="password" v-model="password"/>
<!--        {{password}}-->
       <button @click = "submit"> ENTRAR</button>
 </div>
</template>

<script lang="ts">

  export default {

    data(){
      return{
          username:"",
          password:"",
        };
    },
    methods:{
      async submit() {
        const data = {username: this.username, password: this.password}
        console.log(data)
        const response = await this.$axios.post("http://localhost:8080/login", data,
          {withCredentials: true});
        // this.$axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
      },
    },
  }
</script>
