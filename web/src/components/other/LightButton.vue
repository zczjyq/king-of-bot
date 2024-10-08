<template>
    <label class="switch">
      <input class="switch__input" type="checkbox" role="switch" v-model="isChecked">
      <span class="switch__base-outer"></span>
      <span class="switch__base-inner"></span>
      <svg class="switch__base-neon" viewBox="0 0 40 24" width="40px" height="24px">
        <defs>
          <filter id="switch-glow">
            <feGaussianBlur result="coloredBlur" stddeviation="1"></feGaussianBlur>
            <feMerge>
              <feMergeNode in="coloredBlur"></feMergeNode>
              <feMergeNode in="SourceGraphic"></feMergeNode>
            </feMerge>
          </filter>
          <linearGradient id="switch-gradient1" x1="0" y1="0" x2="1" y2="0">
            <stop offset="0%" stop-color="hsl(var(--on-hue1),90%,70%)" />
            <stop offset="100%" stop-color="hsl(var(--on-hue2),90%,70%)" />
          </linearGradient>
          <linearGradient id="switch-gradient2" x1="0.7" y1="0" x2="0.3" y2="1">
            <stop offset="25%" stop-color="hsla(var(--on-hue1),90%,70%,0)" />
            <stop offset="50%" stop-color="hsla(var(--on-hue1),90%,70%,0.3)" />
            <stop offset="100%" stop-color="hsla(var(--on-hue2),90%,70%,0.3)" />
          </linearGradient>
        </defs>
        <path fill="none" filter="url(#switch-glow)" stroke="url(#switch-gradient1)" stroke-width="1"
          stroke-dasharray="0 104.26 0" stroke-dashoffset="0.01" stroke-linecap="round"
          d="m.5,12C.5,5.649,5.649.5,12,.5h16c6.351,0,11.5,5.149,11.5,11.5s-5.149,11.5-11.5,11.5H12C5.649,23.5.5,18.351.5,12Z" />
      </svg>
      <span class="switch__knob-shadow"></span>
      <span class="switch__knob-container">
        <span class="switch__knob">
          <svg class="switch__knob-neon" viewBox="0 0 48 48" width="48px" height="48px">
            <circle fill="none" stroke="url(#switch-gradient2)" stroke-dasharray="0 90.32 0 54.19"
              stroke-linecap="round" stroke-width="1" r="23" cx="24" cy="24" transform="rotate(-112.5,24,24)" />
          </svg>
        </span>
      </span>
      <span class="switch__led"></span>
      <span class="switch__text">Power</span>
    </label>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isChecked: false // 用于存储开关的状态
      };
    },
    watch: {
      // 监控开关状态的变化，可以在这里执行额外的逻辑
      isChecked(newVal) {
        console.log("Switch is now", newVal ? "ON" : "OFF");
      }
    }
  };
  </script>
  
  <style scoped>
  /* 原来的 CSS 内容可以直接放在这里 */
  * {
    border: 0;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }
  :root {
    --hue: 223;
    --off-hue: 3;
    --on-hue1: 123;
    --on-hue2: 168;
    --fg: hsl(var(--hue), 10%, 90%);
    --primary: hsl(var(--hue), 90%, 50%);
    --trans-dur: 0.6s;
    --trans-timing: cubic-bezier(0.65, 0, 0.35, 1);
    font-size: calc(40px + (60 - 40) * (100vw - 320px) / (2560 - 320));
  }
  body,
  input {
    font: 1em/1.5 sans-serif;
  }
  body {
    background-image: linear-gradient(45deg, hsl(var(--hue), 10%, 20%), hsl(var(--hue), 10%, 10%));
    color: var(--fg);
    display: flex;
    height: 100vh;
    transition: background-color var(--trans-dur), color var(--trans-dur);
  }
  .switch,
  .switch__input {
    -webkit-tap-highlight-color: #0000;
  }
  .switch {
    display: block;
    margin: auto;
    position: relative;
    width: 5em;
    height: 3em;
  }
  .switch__base-outer,
  .switch__base-inner {
    display: block;
    position: absolute;
  }
  .switch__base-outer {
    border-radius: 1.25em;
    box-shadow: -0.125em -0.125em 0.25em hsl(var(--hue), 10%, 30%),
      0.125em 0.125em 0.125em hsl(var(--hue), 10%, 30%) inset, 0.125em 0.125em 0.25em hsl(0, 0%, 0%),
      -0.125em -0.125em 0.125em hsl(var(--hue), 10%, 5%) inset;
    top: 0.125em;
    left: 0.125em;
    width: 4.75em;
    height: 2.75em;
  }
  /* 省略部分样式 */
  </style>
  