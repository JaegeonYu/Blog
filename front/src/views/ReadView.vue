<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
})

const post = ref({
  id: 0,
  title: "",
  content: ""
});
const router = useRouter();

const moveToEdit = () => {
  router.push({name: "edit", params: {postId: props.postId}})
}
const moveToDelete = () =>{
  axios.delete(`/api/posts/${props.postId}`).then(()=>{
    router.replace({name : "home"})
  })
}
onMounted(() => {
  axios.get(`/api/posts/${props.postId}`).then((response) => {
    post.value = response.data;
  })
})
</script>
<template>
  <h2>{{ post.title }}</h2>
  <div>{{ post.content }}</div>
  <el-button type="warning" @click="moveToEdit()">수정하기</el-button>
  <el-button type="warning" @click="moveToDelete()">삭제하기</el-button>

</template>