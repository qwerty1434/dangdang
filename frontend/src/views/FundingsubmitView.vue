<template>
  <div>
    <h1>this is a funding page</h1>
    <div>
      <div class="categorybox"></div>
      <inpu class="categoryselect"></inpu>
      <div class="categorybutton"></div>
    </div>
    <div>
      <div class="fundingtitlebox"></div>
      <input type="text" class="fundingtitleinput" />
    </div>
    <div>
      <div class="goalbox"></div>
      <input type="text" class="goalinput" />
    </div>
    <div>
      <div class="thumbnail1"></div>
      <div class="thumbnail2"></div>
      <div class="thumbnail3"></div>
      <div class="detail1"></div>
      <div class="detail2"></div>
      <div class="detail3"></div>
    </div>

    <div>
      <div class="a">펀딩</div>
      <div class="b">카테고리</div>
      <div class="c">펀딩 제목</div>
      <div class="d">목표 금액</div>
      <div class="e">리워드</div>
      <div class="f">제목</div>
      <div class="g">내용</div>
      <div class="h">금액</div>
      <div class="i">기타정보</div>
      <div class="j">사업자 등록번호</div>
      <div class="k">회사명</div>
      <div class="l">검색용 태그</div>
      <div class="m">펀딩 일정</div>
      <div class="n">썸네일</div>
      <div class="o">본문</div>
      <div class="p">리워드추가</div>
      <div class="q">변경사항취소</div>
      <div class="r">임시저장</div>
      <div class="s">등록하기</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.categorybox {
  box-sizing: border-box;

  position: absolute;
  width: 480px;
  height: 64px;
  left: 592px;
  top: 478px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.fundingtitlebox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 578px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.fundingtitleinput {
  position: absolute;
  width: 900px;
  height: 20px;
  left: 620px;
  top: 600px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.goalbox {
  box-sizing: border-box;

  position: absolute;
  width: 960px;
  height: 64px;
  left: 592px;
  top: 678px;

  border: 0.5px solid #000000;
  border-radius: 5px;
}
.goalinput {
  position: absolute;
  width: 900px;
  height: 20px;
  left: 620px;
  top: 700px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;

  color: #000000;
}
.thumbnail1 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 670px;
  top: 818px;
  background: url("@/assets/picture.png");
}
.thumbnail2 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 811px;
  top: 818px;
  background: url("@/assets/picture.png");
}
.thumbnail3 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 952px;
  top: 818px;
  background: url("@/assets/picture.png");
}
.detail1 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1170px;
  top: 818px;

  background: url("@/assets/picture.png");
}
.detail2 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1311px;
  top: 818px;
  background: url("@/assets/picture.png");
}
.detail3 {
  position: absolute;
  width: 100px;
  height: 100px;
  left: 1452px;
  top: 818px;

  background: url("@/assets/picture.png");
}
.a {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 380px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.b {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 500px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.c {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 600px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.d {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 700px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.e {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 1058px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.f {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1178px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.g {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1278px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.h {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 330px;
  top: 1378px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.i {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 1892px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.j {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2012px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.k {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2112px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.l {
  position: absolute;
  width: 180px;
  height: 20px;
  left: 250px;
  top: 2212px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 23px;
  text-align: right;

  color: #000000;
}
.m {
  position: absolute;
  width: 180px;
  height: 40px;
  left: 250px;
  top: 2404px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 40px;
  line-height: 45px;
  text-align: right;

  color: #000000;
}
.n {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 592px;
  top: 818px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.o {
  position: absolute;
  width: 100px;
  height: 20px;
  left: 1092px;
  top: 818px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;

  color: #000000;
}
.p {
  position: absolute;
  width: 120px;
  height: 20px;
  left: 1422px;
  top: 1468px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;
  text-align: center;

  color: #ffffff;
}
.q {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1313px;
  top: 2538px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
.r {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1312px;
  top: 2711px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
.s {
  position: absolute;
  width: 240px;
  height: 32px;
  left: 1313px;
  top: 2884px;

  font-family: "NanumSquare";
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 36px;
  text-align: center;

  color: #000000;
}
</style>
