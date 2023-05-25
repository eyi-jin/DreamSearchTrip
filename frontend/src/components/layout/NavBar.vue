<template>
  <div v-show="isLogin">
    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close">
          <span class="icofont-close js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>

    <nav class="site-nav mt-3">
      <div class="container">
        <div class="site-navigation">
          <div class="row">
            <div class="col-6 col-lg-3">
              <router-link to="/" class="logo m-0 float-start">Dream Search Tour</router-link>
            </div>
            <div class="col-lg-6 d-none d-lg-inline-block text-center nav-center-wrap">
              <ul class="js-clone-nav text-center site-menu p-0 m-0">
                <!-- class에 active를 추가하면 색이 바뀜 -->
                <li class=""><router-link to="/">Main</router-link></li>
                <li>
                  <router-link to="/search">SearchTrip</router-link>
                </li>
                <li><router-link to="/board">Board</router-link></li>
                <li><router-link to="/weather">weather</router-link></li>
                <li><router-link to="/my">Mypage</router-link></li>
              </ul>
            </div>
            <div class="col-6 col-lg-3 text-lg-end">
              <ul class="js-clone-nav d-none d-lg-inline-block text-end site-menu">
                <li class="cta-button">
                  <router-link to="/login">Logout</router-link>
                </li>
              </ul>

              <a
                id="burger"
                href="#"
                class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
                data-toggle="collapse"
                data-target="#main-navbar"
              >
                <span></span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  props: ["isLogin", "userInfo"],
  methods: {
    siteMenuClone() {
      var jsCloneNavs = document.querySelectorAll(".js-clone-nav");
      var siteMobileMenuBody = document.querySelector(".site-mobile-menu-body");

      jsCloneNavs.forEach((nav) => {
        var navCloned = nav.cloneNode(true);
        navCloned.setAttribute("class", "site-nav-wrap");
        siteMobileMenuBody.appendChild(navCloned);
      });

      setTimeout(function () {
        var hasChildrens = document
          .querySelector(".site-mobile-menu")
          .querySelectorAll(" .has-children");

        var counter = 0;
        hasChildrens.forEach((hasChild) => {
          var refEl = hasChild.querySelector("a");

          var newElSpan = document.createElement("span");
          newElSpan.setAttribute("class", "arrow-collapse collapsed");

          // prepend equivalent to jquery
          hasChild.insertBefore(newElSpan, refEl);

          var arrowCollapse = hasChild.querySelector(".arrow-collapse");
          arrowCollapse.setAttribute("data-toggle", "collapse");
          arrowCollapse.setAttribute("data-target", "#collapseItem" + counter);

          var dropdown = hasChild.querySelector(".dropdown");
          dropdown.setAttribute("class", "collapse");
          dropdown.setAttribute("id", "collapseItem" + counter);

          counter++;
        });
      }, 1000);

      // Click js-menu-toggle

      var menuToggle = document.querySelectorAll(".js-menu-toggle");
      var mTog;
      menuToggle.forEach((mtoggle) => {
        mTog = mtoggle;
        mtoggle.addEventListener("click", () => {
          if (document.body.classList.contains("offcanvas-menu")) {
            document.body.classList.remove("offcanvas-menu");
            mtoggle.classList.remove("active");
            mTog.classList.remove("active");
          } else {
            document.body.classList.add("offcanvas-menu");
            mtoggle.classList.add("active");
            mTog.classList.add("active");
          }
        });
      });

      var specifiedElement = document.querySelector(".site-mobile-menu");
      console.log(specifiedElement);
      var mt, mtoggleTemp;
      document.addEventListener("click", function (event) {
        var isClickInside = specifiedElement.contains(event.target);
        menuToggle.forEach((mtoggle) => {
          mtoggleTemp = mtoggle;
          mt = mtoggle.contains(event.target);
        });

        if (!isClickInside && !mt) {
          if (document.body.classList.contains("offcanvas-menu")) {
            document.body.classList.remove("offcanvas-menu");
            mtoggleTemp.classList.remove("active");
          }
        }
      });
    },
    closeMobileMenu() {
      document.body.classList.remove("offcanvas-menu");
      document.querySelector("#burger").classList.remove("active");
    },
  },
  mounted() {
    this.siteMenuClone();
  },
  watch: {
    $route(to, form) {
      if (to.path !== form.patch && to.path == "/login") {
        this.closeMobileMenu();
      }
    },
  },
};
</script>

<style></style>
