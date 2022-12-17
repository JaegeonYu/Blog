<script setup lang="ts">
import axios from "axios";
import {useRouter} from "vue-router";
import {ref} from "vue";

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
});


const router = useRouter();

const post = ref({
  id: 0,
  title: "",
  content: ""
})
axios.get(`/api/posts/${props.postId}`).then((response) => {
  post.value = response.data;
})

const edit = () => {
  axios.patch(`/api/posts/${props.postId}`, post.value).then(() => {
    router.replace({name: "home"})
  });
};
</script>
<template>
  <div>
    <el-input v-model="post.title" placeholder="제목을 입력하세요"/>
  </div>

  <div class="mt-2">
    <el-input v-model="post.content" type="textarea" rows="15"></el-input>
  </div>

  <div class="mt-2">
    <el-button @click="edit()" type="primary">글 수정완료</el-button>
  </div>

</template>