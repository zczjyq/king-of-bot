<template>
  <div
    id="carouselExample"
    class="carousel slide"
    data-bs-ride="carousel"
    data-bs-interval="1000"
  >
    <div class="carousel-inner">
      <div class="carousel-item active">
        <div
          class="d-flex justify-content-center align-items-center"
          style="height: 40px"
        >
          <h6>第一条公告信息</h6>
        </div>
      </div>
      <div class="carousel-item">
        <div
          class="d-flex justify-content-center align-items-center"
          style="height: 40px"
        >
          <h6>第二条公告信息</h6>
        </div>
      </div>
      <div class="carousel-item">
        <div
          class="d-flex justify-content-center align-items-center"
          style="height: 40px"
        >
          <h6>第三条公告信息</h6>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#carouselExample .carousel-inner {
  height: 40px;
}

/* Remove any extra padding or margin */
.carousel-item h6 {
  margin: 0;
}

/* Hide the carousel controls */
.carousel-control-prev,
.carousel-control-next {
  display: none;
}
</style>