<template>
  <div
    id="carouselExampleAutoplaying"
    class="carousel slide"
    data-bs-ride="carousel"
  >
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@/assets/img/carousel/image1.png" class="d-block w-100" />
      </div>
      <div class="carousel-item active">
        <img src="@/assets/img/carousel/image2.png" class="d-block w-100" />
      </div>
      <div class="carousel-item active">
        <img src="@/assets/img/carousel/image3.png" class="d-block w-100" />
      </div>
      <div class="carousel-item active">
        <img src="@/assets/img/carousel/image4.png" class="d-block w-100" />
      </div>
      <div class="carousel-item active">
        <img src="@/assets/img/carousel/image5.png" class="d-block w-100" />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleAutoplaying"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleAutoplaying"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>
