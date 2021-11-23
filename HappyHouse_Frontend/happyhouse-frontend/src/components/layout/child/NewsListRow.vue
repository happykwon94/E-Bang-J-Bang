<template>
  <li>
    <dl>
      <dt class="photo">
        <a :href="url">
          <img :src="imgUrl" alt="" />
        </a>
      </dt>
      <dt>
        <a :href="url">{{ title }}</a>
      </dt>
      <dd>
        {{ summary }}
        <span>{{ agency }}</span> /
        <span>{{ pubDate }}</span>
      </dd>
    </dl>
  </li>
</template>

<script>
export default {
  name: "NewsListRow",
  props: {
    title: String,
    imgUrl: String,
    url: String,
    summary: String,
    agency: String,
    pubDate: String,
  },
};
</script>

<style>
li {
  list-style: none;
}

dl {
  width: 100%;
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}

dl dt {
  font-size: 14px;
  font-weight: 700;
  line-height: 20px;
}

dt a {
  color: #000063;
  font-size: 0.9rem;
}

dt {
  display: block;
}

.photo {
  display: inline;
  float: left;
  position: relative;
  margin: 3px 6px 0 0;
}

dd {
  font-size: 0.6rem;
}

li dl::after {
  display: block;
  clear: both;
  content: "";
}
</style>
