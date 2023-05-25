<template>
  <div
    id="app"
    :class="typeof weather.main != 'undefined' && Math.round(weather.main.temp) > 16 ? 'warm' : ''"
  >
    <main>
      <div
        class="hero overlay d-flex justify-content-center"
        style="background-color: #ffffff; background-color: rgba(0, 0, 0, 0)"
      >
        <div class="container">
          <div class="row align-items-center justify-content-start mt-4">
            <div class="col-lg-6 mx-auto text-center">
              <h1 class="heading" data-aos="fade-up">Weather</h1>
              <p data-aos="fade-up">
                The weather of the mind is always changing. We look for doors that open our minds,
                and explore the inner landscape.
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="search-box">
        <input type="text" class="search-bar" placeholder="Search..." v-model="query" />
        <!-- @keypress="fetchWeather" -->
      </div>
      <div class="weather-wrap" v-if="typeof weather.main != 'undefined'">
        <div class="location-box">
          <div class="location">{{ weather.name }}, {{ weather.sys.country }}</div>
          <div class="date">{{ dateBuilder() }}</div>
        </div>
        <div class="weather-box">
          <div class="temp">{{ Math.round(weather.main.temp) }}℃</div>
          <div class="weather">{{ weather.weather[0].main }}</div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
let weatherList = new Map([
  ["서울", "seoul"],
  ["인천", "incheon"],
  ["대구", "daegu"],
  ["부산", "busan"],
  ["제주", "jeju"],
  ["울산", "ulsan"],
  ["대전", "daejeon"],
  ["광주", "gwangju"],
]);
export default {
  data: function () {
    return {
      api_key: process.env.VUE_APP_WEATHER_API_KEY,
      url_base: "https://api.openweathermap.org/data/2.5/",
      query: "",
      query2: "",
      weather: {},
    };
  },
  methods: {
    fetchWeather: function () {
      console.log(this.api_key);
      //   if (e.key == "Enter") {
      let fetchUrl = `${this.url_base}weather?q=${this.query2}&units=metric&APPID=${this.api_key}`;
      fetch(fetchUrl)
        .then((res) => {
          console.log(res);
          return res.json();
        })
        .then((results) => {
          return this.setResult(results);
        });
      //   }
    },
    setResult: function (results) {
      this.weather = results;
    },
    dateBuilder: function () {
      let d = new Date();
      let months = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
      let days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      let day = days[d.getDay()];
      let date = d.getDate();
      let month = months[d.getMonth()];
      let year = d.getFullYear();
      return `${day} ${date} ${month} ${year}`;
    },
  },
  created() {
    if (!sessionStorage.getItem("userName")) {
      this.$router.push("/login");
    }
  },
  watch: {
    query() {
      // if (weatherList.get(this.query)) {
      //   this.query2 = weatherList.get(this.query);
      // } else {
      //   this.query2 = this.query;
      // }
      this.query2 = weatherList.get(this.query) ? weatherList.get(this.query) : this.query;
    },
    query2() {
      this.fetchWeather();
    },
  },
};
</script>

<style scoped>
.hero.overlay:before {
  background-color: rgba(0, 0, 0, 0) !important;
}
.hero > .container > .row {
  padding-bottom: 0rem;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  font-family: "montserrat", sans-serif;
}
#app {
  background-image: url("@/assets/cold3.jpg");
  background-size: cover;
  background-position: bottom;
  transition: 0.4s;
}
#app.warm {
  background-image: url("@/assets/warm3.jpg");
}
main {
  min-height: 80vh;
  padding: 25px;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.25), rgba(0, 0, 0, 0.75));
}
.search-box {
  width: 100%;
  margin-bottom: 30px;
  margin-top: 70px;
}
.search-box .search-bar {
  display: block;
  width: 100%;
  padding: 15px;

  color: #313131;
  font-size: 20px;
  appearance: none;
  border: none;
  outline: none;
  background: none;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 0px 16px 0px 16px;
  transition: 0.4s;
}
.search-box .search-bar:focus {
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.75);
  border-radius: 16px 0px 16px 0px;
}
.location-box .location {
  color: #fff;
  font-size: 32px;
  font-weight: 500;
  text-align: center;
  text-shadow: 1px 3px rgba(0, 0, 0, 0.25);
}
.location-box .date {
  color: #fff;
  font-size: 20px;
  font-weight: 300;
  font-style: italic;
  text-align: center;
}
.weather-box {
  text-align: center;
}
.weather-box .temp {
  display: inline-block;
  padding: 10px 25px;
  color: #fff;
  font-size: 102px;
  font-weight: 900;
  text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.25);
  border-radius: 16px;
  margin: 30px 0px;
  box-shadow: 3px 6px rgba(0, 0, 0, 0.25);
}
.weather-box .weather {
  color: #fff;
  font-size: 48px;
  font-weight: 700;
  font-style: italic;
  text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
}
</style>
