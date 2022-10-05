<template>
  <div>
    <div class="board"></div>
    <div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'fashion' } }"
          class="fashion"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'beauty' } }"
          class="beauty"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'food' } }"
          class="food"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'tech' } }"
          class="tech"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'kid' } }"
          class="kid"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'pet' } }"
          class="pet"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'culture' } }"
          class="culture"
        ></router-link>
      </div>
      <div>
        <router-link
          :to="{ path: '/funding', query: { id: 'sports' } }"
          class="sports"
        ></router-link>
      </div>
    </div>

    <div class=""></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.board {
  position: absolute;
  width: 240px;
  height: 320px;
  left: 0px;
  top: 0px;
  background: url("@/assets/category.png");
}
.fashion {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 0px;
}
.beauty {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 40px;
}
.food {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 80px;
}
.tech {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 120px;
}
.kid {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 160px;
}
.pet {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 200px;
}
.culture {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 240px;
}
.sports {
  position: absolute;
  width: 240px;
  height: 40px;
  left: 0px;
  top: 280px;
}
</style>
