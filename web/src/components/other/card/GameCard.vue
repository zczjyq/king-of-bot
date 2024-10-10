<template>
  <section id="card1" class="card">
    <svg
      viewBox="0 0 16 16"
      class="bi bi-image-fill"
      fill="currentColor"
      height="40"
      width="40"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M.002 3a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-12a2 2 0 0 1-2-2V3zm1 9v1a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V9.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12zm5-6.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0z"
      ></path>
    </svg>
    <div class="card__content">
      <p class="card__title">Lorem Ipsum</p>
      <p class="card__description">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vitae
        justo vel lorem tincidunt ultrices at non nunc. Donec in sapien viverra,
        tincidunt augue id, efficitur massa.
      </p>
    </div>
  </section>
</template>

<style scoped>
/* From Uiverse.io by samuelfcosta18 */
section.card {
  position: relative;
  width: 250px;
  height: 250px;
  background-color: #f2f2f2;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  perspective: 1000px;
  transition: all 0.8s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}
.card svg {
  fill: #000;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  border-radius: 3px;
}
.card:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 16px #000000;
  background-color: #f2f2f2;
  color: #ffffff;
}

.card__content {
  color: #000000;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
  background-color: #f2f2f2;
  transform: rotateX(-90deg);
  transform-origin: bottom;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card:hover .card__content {
  transform: rotateX(0deg);
}

.card__title {
  margin: 0;
  padding-left: 5px;
  font-size: 24px;
  color: #000000;
  font-weight: 700;
}

.card:hover svg {
  scale: 0;
}

.card__description {
  margin: 10px 0 0;
  font-size: 14px;
  color: #000000;
  line-height: 1.4;
}
/* Commands to change the shadows in dark mode
@media (prefers-color-scheme: dark) {
  .card:hover {
  box-shadow: 0 8px 16px #000000;
  }
}*/
</style>