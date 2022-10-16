import {defineConfig} from "cypress";

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
    },
    includeShadowDom: true,
    video: false,
    screenshotOnRunFailure: false,
    baseUrl: "http://localhost:8080"
  },
  chromeWebSecurity: false
});
