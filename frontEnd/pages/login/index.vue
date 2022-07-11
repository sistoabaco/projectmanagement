<template>
 <div>
   <form @submit.prevent = "submit">
     <div v-if="error" class="alert alert-danger" role="alert">
       {{error}}
     </div>
   <h2>E-mail</h2>
       <input type="text" placeholder="email address" v-model="username"/>
<!--        {{email}}-->

       <h2>Password</h2>
       <input type="password" placeholder="password" v-model="password"/>
<!--        {{password}}-->
       <button class="btn btn-primary btn-block"> ENTRAR</button>
   </form>
 </div>
</template>

<script lang="ts">

  export default {

    data(){

      return{
          username:'',
          password:'',
          error:'',
        }
    },

    methods:{
      async submit() {
        const data = {username: this.username, password: this.password};
        // console.log(data)
        try{
          const response = await this.$axios.post('http://localhost:8080/login', data ,
            {withCredentials:'include'});

        this.$axios.defaults.headers.common['Authorization'] = 'Bearer  + response.data.token';

        }catch (e){
          this.error = "invalid user and pass";
        }
      },
    },
  }
</script>
