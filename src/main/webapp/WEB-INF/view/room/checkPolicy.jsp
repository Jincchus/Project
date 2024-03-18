<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>policy 화면 체크</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
/**
* Template Name: Mentor
* Updated: Mar 10 2024 with Bootstrap v5.3.3
* Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/

/*--------------------------------------------------------------
# General
--------------------------------------------------------------*/
body {
  font-family: "Open Sans", sans-serif;
  color: #444444;
}

a {
  color: #5fcf80;
  text-decoration: none;
}

a:hover {
  color: #86db9f;
  text-decoration: none;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: "Raleway", sans-serif;
}

/*--------------------------------------------------------------
# Back to top button
--------------------------------------------------------------*/
.back-to-top {
  position: fixed;
  visibility: hidden;
  opacity: 0;
  right: 15px;
  bottom: 15px;
  z-index: 996;
  background: #5fcf80;
  width: 40px;
  height: 40px;
  border-radius: 50px;
  transition: all 0.4s;
}

.back-to-top i {
  font-size: 28px;
  color: #fff;
  line-height: 0;
}

.back-to-top:hover {
  background: #7ed899;
  color: #fff;
}

.back-to-top.active {
  visibility: visible;
  opacity: 1;
}

/*--------------------------------------------------------------
# Preloader
--------------------------------------------------------------*/
#preloader {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9999;
  overflow: hidden;
  background: #fff;
}

#preloader:before {
  content: "";
  position: fixed;
  top: calc(50% - 30px);
  left: calc(50% - 30px);
  border: 6px solid #5fcf80;
  border-top-color: #fff;
  border-bottom-color: #fff;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  animation: animate-preloader 1s linear infinite;
}

@keyframes animate-preloader {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

/*--------------------------------------------------------------
# Disable aos animation delay on mobile devices
--------------------------------------------------------------*/
@media screen and (max-width: 768px) {
  [data-aos-delay] {
    transition-delay: 0 !important;
  }
}

/*--------------------------------------------------------------
# Header
--------------------------------------------------------------*/
#header {
  background: #fff;
  transition: all 0.5s;
  z-index: 997;
  padding: 15px 0;
  box-shadow: 0px 0 18px rgba(55, 66, 59, 0.08);
}

#header .logo {
  font-size: 30px;
  margin: 0;
  padding: 0;
  line-height: 1;
  font-weight: 600;
  letter-spacing: 1px;
  text-transform: uppercase;
  font-family: "Poppins", sans-serif;
}

#header .logo a {
  color: #5fcf80;
}

#header .logo img {
  max-height: 40px;
}

/**
* Get Startet Button 
*/
.get-started-btn {
  margin-left: 22px;
  background: #5fcf80;
  color: #fff;
  border-radius: 50px;
  padding: 8px 25px;
  white-space: nowrap;
  transition: 0.3s;
  font-size: 14px;
  display: inline-block;
}

.get-started-btn:hover {
  background: #3ac162;
  color: #fff;
}

@media (max-width: 768px) {
  .get-started-btn {
    margin: 0 15px 0 0;
    padding: 6px 18px;
  }
}

/*--------------------------------------------------------------
# Navigation Menu
--------------------------------------------------------------*/
/**
* Desktop Navigation 
*/
.navbar {
  padding: 0;
}

.navbar ul {
  margin: 0;
  padding: 0;
  display: flex;
  list-style: none;
  align-items: center;
}

.navbar li {
  position: relative;
}

.navbar a,
.navbar a:focus {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0 10px 30px;
  font-family: "Poppins", sans-serif;
  font-size: 15px;
  font-weight: 500;
  color: #37423b;
  white-space: nowrap;
  transition: 0.3s;
}

.navbar a i,
.navbar a:focus i {
  font-size: 12px;
  line-height: 0;
  margin-left: 5px;
}

.navbar a:hover,
.navbar .active,
.navbar .active:focus,
.navbar li:hover>a {
  color: #5fcf80;
}

.navbar .dropdown ul {
  display: block;
  position: absolute;
  left: 30px;
  top: calc(100% + 30px);
  margin: 0;
  padding: 10px 0;
  z-index: 99;
  opacity: 0;
  visibility: hidden;
  background: #fff;
  box-shadow: 0px 0px 30px rgba(127, 137, 161, 0.25);
  transition: 0.3s;
  border-radius: 4px;
}

.navbar .dropdown ul li {
  min-width: 200px;
}

.navbar .dropdown ul a {
  padding: 10px 20px;
  font-size: 14px;
  text-transform: none;
  font-weight: 500;
}

.navbar .dropdown ul a i {
  font-size: 12px;
}

.navbar .dropdown ul a:hover,
.navbar .dropdown ul .active:hover,
.navbar .dropdown ul li:hover>a {
  color: #5fcf80;
}

.navbar .dropdown:hover>ul {
  opacity: 1;
  top: 100%;
  visibility: visible;
}

.navbar .dropdown .dropdown ul {
  top: 0;
  left: calc(100% - 30px);
  visibility: hidden;
}

.navbar .dropdown .dropdown:hover>ul {
  opacity: 1;
  top: 0;
  left: 100%;
  visibility: visible;
}

@media (max-width: 1366px) {
  .navbar .dropdown .dropdown ul {
    left: -90%;
  }

  .navbar .dropdown .dropdown:hover>ul {
    left: -100%;
  }
}

/**
* Mobile Navigation 
*/
.mobile-nav-toggle {
  color: #37423b;
  font-size: 28px;
  cursor: pointer;
  display: none;
  line-height: 0;
  transition: 0.5s;
}

.mobile-nav-toggle.bi-x {
  color: #fff;
}

@media (max-width: 991px) {
  .mobile-nav-toggle {
    display: block;
  }

  .navbar ul {
    display: none;
  }
}

.navbar-mobile {
  position: fixed;
  overflow: hidden;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: rgba(32, 38, 34, 0.9);
  transition: 0.3s;
  z-index: 999;
}

.navbar-mobile .mobile-nav-toggle {
  position: absolute;
  top: 15px;
  right: 15px;
}

.navbar-mobile ul {
  display: block;
  position: absolute;
  top: 55px;
  right: 15px;
  bottom: 15px;
  left: 15px;
  padding: 10px 0;
  border-radius: 6px;
  background-color: #fff;
  overflow-y: auto;
  transition: 0.3s;
}

.navbar-mobile a,
.navbar-mobile a:focus {
  padding: 10px 20px;
  font-size: 15px;
  color: #37423b;
}

.navbar-mobile a:hover,
.navbar-mobile .active,
.navbar-mobile li:hover>a {
  color: #5fcf80;
}

.navbar-mobile .getstarted,
.navbar-mobile .getstarted:focus {
  margin: 15px;
}

.navbar-mobile .dropdown ul {
  position: static;
  display: none;
  margin: 10px 20px;
  padding: 10px 0;
  z-index: 99;
  opacity: 1;
  visibility: visible;
  background: #fff;
  box-shadow: 0px 0px 30px rgba(127, 137, 161, 0.25);
}

.navbar-mobile .dropdown ul li {
  min-width: 200px;
}

.navbar-mobile .dropdown ul a {
  padding: 10px 20px;
}

.navbar-mobile .dropdown ul a i {
  font-size: 12px;
}

.navbar-mobile .dropdown ul a:hover,
.navbar-mobile .dropdown ul .active:hover,
.navbar-mobile .dropdown ul li:hover>a {
  color: #5fcf80;
}

.navbar-mobile .dropdown>.dropdown-active {
  display: block;
}

/*--------------------------------------------------------------
# Hero Section
--------------------------------------------------------------*/
#hero {
  width: 100%;
  height: 80vh;
  background: url("../img/hero-bg.jpg") top center;
  background-size: cover;
  position: relative;
}

#hero:before {
  content: "";
  background: rgba(0, 0, 0, 0.4);
  position: absolute;
  bottom: 0;
  top: 0;
  left: 0;
  right: 0;
}

#hero .container {
  padding-top: 72px;
}

@media (max-width: 992px) {
  #hero .container {
    padding-top: 62px;
  }
}

#hero h1 {
  margin: 0;
  font-size: 48px;
  font-weight: 700;
  line-height: 56px;
  color: #fff;
  font-family: "Poppins", sans-serif;
}

#hero h2 {
  color: #eee;
  margin: 10px 0 0 0;
  font-size: 24px;
}

#hero .btn-get-started {
  font-family: "Raleway", sans-serif;
  font-weight: 500;
  font-size: 15px;
  letter-spacing: 1px;
  display: inline-block;
  padding: 10px 35px;
  border-radius: 50px;
  transition: 0.5s;
  margin-top: 30px;
  border: 2px solid #fff;
  color: #fff;
}

#hero .btn-get-started:hover {
  background: #5fcf80;
  border: 2px solid #5fcf80;
}

@media (min-width: 1024px) {
  #hero {
    background-attachment: fixed;
  }
}

@media (max-width: 768px) {
  #hero {
    height: 100vh;
  }

  #hero h1 {
    font-size: 28px;
    line-height: 36px;
  }

  #hero h2 {
    font-size: 18px;
    line-height: 24px;
  }
}

/*--------------------------------------------------------------
# Sections General
--------------------------------------------------------------*/
section {
  padding: 60px 0;
  overflow: hidden;
}

.section-bg {
  background-color: #f6f7f6;
}

.section-title {
  padding-bottom: 40px;
}

.section-title h2 {
  font-size: 14px;
  font-weight: 500;
  padding: 0;
  line-height: 1px;
  margin: 0 0 5px 0;
  letter-spacing: 2px;
  text-transform: uppercase;
  color: #aaaaaa;
  font-family: "Poppins", sans-serif;
}

.section-title h2::after {
  content: "";
  width: 120px;
  height: 1px;
  display: inline-block;
  background: #9ae1af;
  margin: 4px 10px;
}

.section-title p {
  margin: 0;
  margin: 0;
  font-size: 36px;
  font-weight: 700;
  text-transform: uppercase;
  font-family: "Poppins", sans-serif;
  color: #37423b;
}

.breadcrumbs {
  margin-top: 73px;
  text-align: center;
  background: #5fcf80;
  padding: 30px 0;
  color: #fff;
}

@media (max-width: 992px) {
  .breadcrumbs {
    margin-top: 63px;
  }
}

.breadcrumbs h2 {
  font-size: 32px;
  font-weight: 500;
}

.breadcrumbs p {
  font-size: 14px;
  margin-bottom: 0;
}

/*--------------------------------------------------------------
# About
--------------------------------------------------------------*/
.about .content h3 {
  font-weight: 600;
  font-size: 26px;
}

.about .content ul {
  list-style: none;
  padding: 0;
}

.about .content ul li {
  padding-bottom: 10px;
}

.about .content ul i {
  font-size: 20px;
  padding-right: 4px;
  color: #5fcf80;
}

.about .content .learn-more-btn {
  background: #5fcf80;
  color: #fff;
  border-radius: 50px;
  padding: 8px 25px 9px 25px;
  white-space: nowrap;
  transition: 0.3s;
  font-size: 14px;
  display: inline-block;
}

.about .content .learn-more-btn:hover {
  background: #3ac162;
  color: #fff;
}

@media (max-width: 768px) {
  .about .content .learn-more-btn {
    margin: 0 48px 0 0;
    padding: 6px 18px;
  }
}

/*--------------------------------------------------------------
# Counts
--------------------------------------------------------------*/
.counts {
  padding: 30px 0;
}

.counts .counters span {
  font-size: 48px;
  display: block;
  color: #5fcf80;
  font-weight: 700;
}

.counts .counters p {
  padding: 0;
  margin: 0 0 20px 0;
  font-family: "Raleway", sans-serif;
  font-size: 15px;
  font-weight: 600;
  color: #37423b;
}

/*--------------------------------------------------------------
# Why Us
--------------------------------------------------------------*/
.why-us .content {
  padding: 30px;
  background: #5fcf80;
  border-radius: 4px;
  color: #fff;
}

.why-us .content h3 {
  font-weight: 700;
  font-size: 34px;
  margin-bottom: 30px;
}

.why-us .content p {
  margin-bottom: 30px;
}

.why-us .content .more-btn {
  display: inline-block;
  background: rgba(255, 255, 255, 0.2);
  padding: 6px 30px 8px 30px;
  color: #fff;
  border-radius: 50px;
  transition: all ease-in-out 0.4s;
}

.why-us .content .more-btn i {
  font-size: 14px;
}

.why-us .content .more-btn:hover {
  color: #5fcf80;
  background: #fff;
}

.why-us .icon-boxes .icon-box {
  text-align: center;
  background: #fff;
  padding: 40px 30px;
  width: 100%;
  border: 1px solid #eef0ef;
}

.why-us .icon-boxes .icon-box i {
  font-size: 32px;
  padding: 18px;
  color: #5fcf80;
  margin-bottom: 30px;
  background: #ecf9f0;
  border-radius: 50px;
}

.why-us .icon-boxes .icon-box h4 {
  font-size: 20px;
  font-weight: 700;
  margin: 0 0 30px 0;
}

.why-us .icon-boxes .icon-box p {
  font-size: 15px;
  color: #848484;
}

/*--------------------------------------------------------------
# Features
--------------------------------------------------------------*/
.features {
  padding-top: 0px;
}

.features .icon-box {
  display: flex;
  align-items: center;
  padding: 20px;
  transition: 0.3s;
  border: 1px solid #eef0ef;
}

.features .icon-box i {
  font-size: 32px;
  padding-right: 10px;
  line-height: 1;
}

.features .icon-box h3 {
  font-weight: 700;
  margin: 0;
  padding: 0;
  line-height: 1;
  font-size: 16px;
}

.features .icon-box h3 a {
  color: #37423b;
  transition: 0.3s;
}

.features .icon-box:hover {
  border-color: #5fcf80;
}

.features .icon-box:hover h3 a {
  color: #5fcf80;
}

/*--------------------------------------------------------------
# Courses
--------------------------------------------------------------*/
.courses .course-item {
  border-radius: 5px;
  border: 1px solid #eef0ef;
}

.courses .course-content {
  padding: 15px;
}

.courses .course-content h3 {
  font-weight: 700;
  font-size: 20px;
}

.courses .course-content h3 a {
  color: #37423b;
  transition: 0.3s;
}

.courses .course-content h3 a:hover {
  color: #5fcf80;
}

.courses .course-content p {
  font-size: 14px;
  color: #777777;
}

.courses .course-content h4 {
  font-size: 14px;
  background: #5fcf80;
  padding: 7px 14px;
  color: #fff;
  margin: 0;
}

.courses .course-content .price {
  margin: 0;
  font-weight: 700;
  font-size: 18px;
  color: #37423b;
}

.courses .trainer {
  padding-top: 15px;
  border-top: 1px solid #eef0ef;
}

.courses .trainer .trainer-profile img {
  max-width: 50px;
  border-radius: 50px;
}

.courses .trainer .trainer-profile span {
  padding-left: 10px;
  font-weight: 600;
  font-size: 16px;
  color: #5a6c60;
}

.courses .trainer .trainer-rank {
  font-size: 18px;
  color: #657a6d;
}

/*--------------------------------------------------------------
# Trainers
--------------------------------------------------------------*/
.trainers .member {
  text-align: center;
  margin-bottom: 20px;
  background: #fff;
  border: 1px solid #eef0ef;
}

.trainers .member img {
  margin: -1px -1px 30px -1px;
}

.trainers .member .member-content {
  padding: 0 20px 30px 20px;
}

.trainers .member h4 {
  font-weight: 700;
  margin-bottom: 2px;
  font-size: 18px;
}

.trainers .member span {
  font-style: italic;
  display: block;
  font-size: 13px;
}

.trainers .member p {
  padding-top: 10px;
  font-size: 14px;
  font-style: italic;
  color: #aaaaaa;
}

.trainers .member .social {
  margin-top: 15px;
}

.trainers .member .social a {
  color: #7e9486;
  transition: 0.3s;
}

.trainers .member .social a:hover {
  color: #5fcf80;
}

.trainers .member .social i {
  font-size: 18px;
  margin: 0 2px;
}

/*--------------------------------------------------------------
# Testimonials
--------------------------------------------------------------*/
.testimonials .testimonial-wrap {
  padding-left: 50px;
}

.testimonials .testimonials-carousel,
.testimonials .testimonials-slider {
  overflow: hidden;
}

.testimonials .testimonial-item {
  box-sizing: content-box;
  padding: 30px 30px 30px 60px;
  margin: 30px 15px;
  min-height: 200px;
  border: 1px solid #eef0ef;
  position: relative;
  background: #fff;
}

.testimonials .testimonial-item .testimonial-img {
  width: 90px;
  border-radius: 10px;
  border: 6px solid #fff;
  position: absolute;
  left: -45px;
}

.testimonials .testimonial-item h3 {
  font-size: 18px;
  font-weight: bold;
  margin: 10px 0 5px 0;
  color: #111;
}

.testimonials .testimonial-item h4 {
  font-size: 14px;
  color: #999;
  margin: 0;
}

.testimonials .testimonial-item .quote-icon-left,
.testimonials .testimonial-item .quote-icon-right {
  color: #c1ecce;
  font-size: 26px;
}

.testimonials .testimonial-item .quote-icon-left {
  display: inline-block;
  left: -5px;
  position: relative;
}

.testimonials .testimonial-item .quote-icon-right {
  display: inline-block;
  right: -5px;
  position: relative;
  top: 10px;
}

.testimonials .testimonial-item p {
  font-style: italic;
  margin: 15px auto 15px auto;
}

.testimonials .swiper-pagination {
  margin-top: 20px;
  position: relative;
}

.testimonials .swiper-pagination .swiper-pagination-bullet {
  width: 12px;
  height: 12px;
  background-color: #fff;
  opacity: 1;
  border: 1px solid #5fcf80;
}

.testimonials .swiper-pagination .swiper-pagination-bullet-active {
  background-color: #5fcf80;
}

@media (max-width: 767px) {
  .testimonials .testimonial-wrap {
    padding-left: 0;
  }

  .testimonials .testimonials-carousel,
  .testimonials .testimonials-slider {
    overflow: hidden;
  }

  .testimonials .testimonial-item {
    padding: 30px;
    margin: 15px;
  }

  .testimonials .testimonial-item .testimonial-img {
    position: static;
    left: auto;
  }
}

/*--------------------------------------------------------------
# Cource Details
--------------------------------------------------------------*/
.course-details h3 {
  font-size: 24px;
  margin: 30px 0 15px 0;
  font-weight: 700;
  position: relative;
  padding-bottom: 10px;
}

.course-details h3:before {
  content: "";
  position: absolute;
  display: block;
  width: 100%;
  height: 1px;
  background: #eef0ef;
  bottom: 0;
  left: 0;
}

.course-details h3:after {
  content: "";
  position: absolute;
  display: block;
  width: 60px;
  height: 1px;
  background: #5fcf80;
  bottom: 0;
  left: 0;
}

.course-details .course-info {
  background: #f6f7f6;
  padding: 10px 15px;
  margin-bottom: 15px;
}

.course-details .course-info h5 {
  font-weight: 400;
  font-size: 16px;
  margin: 0;
  font-family: "Poppins", sans-serif;
}

.course-details .course-info p {
  margin: 0;
  font-weight: 600;
}

.course-details .course-info a {
  color: #657a6d;
}

/*--------------------------------------------------------------
# Cource Details Tabs
--------------------------------------------------------------*/
.cource-details-tabs {
  overflow: hidden;
  padding-top: 0;
}

.cource-details-tabs .nav-tabs {
  border: 0;
}

.cource-details-tabs .nav-link {
  border: 0;
  padding: 12px 15px 12px 0;
  transition: 0.3s;
  color: #37423b;
  border-radius: 0;
  border-right: 2px solid #e2e7e4;
  font-weight: 600;
  font-size: 15px;
}

.cource-details-tabs .nav-link:hover {
  color: #5fcf80;
}

.cource-details-tabs .nav-link.active {
  color: #5fcf80;
  border-color: #5fcf80;
}

.cource-details-tabs .tab-pane.active {
  animation: fadeIn 0.5s ease-out;
}

.cource-details-tabs .details h3 {
  font-size: 26px;
  font-weight: 600;
  margin-bottom: 20px;
  color: #37423b;
}

.cource-details-tabs .details p {
  color: #777777;
}

.cource-details-tabs .details p:last-child {
  margin-bottom: 0;
}

@media (max-width: 992px) {
  .cource-details-tabs .nav-link {
    border: 0;
    padding: 15px;
  }

  .cource-details-tabs .nav-link.active {
    color: #fff;
    background: #5fcf80;
  }
}
/*--------------------------------------------------------------
# Events
--------------------------------------------------------------*/
.events .card {
  border: 0;
  padding: 0 30px;
  margin-bottom: 60px;
  position: relative;
}

.events .card-img {
  width: calc(100% + 60px);
  margin-left: -30px;
  overflow: hidden;
  z-index: 9;
  border-radius: 0;
}

.events .card-img img {
  max-width: 100%;
  transition: all 0.3s ease-in-out;
}

.events .card-body {
  z-index: 10;
  background: #fff;
  border-top: 4px solid #fff;
  padding: 30px;
  box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
  margin-top: -60px;
  transition: 0.3s;
}

.events .card-title {
  font-weight: 700;
  text-align: center;
  margin-bottom: 20px;
}

.events .card-title a {
  color: #37423b;
  transition: 0.3s;
}

.events .card-text {
  color: #5e5e5e;
}

.events .read-more a {
  color: #777777;
  text-transform: uppercase;
  font-weight: 600;
  font-size: 12px;
  transition: 0.3s;
}

.events .read-more a:hover {
  color: #5fcf80;
}

.events .card:hover img {
  transform: scale(1.1);
}

.events .card:hover .card-body {
  border-color: #5fcf80;
}

.events .card:hover .card-body .card-title a {
  color: #5fcf80;
}

/*--------------------------------------------------------------
# Pricing
--------------------------------------------------------------*/
.pricing .box {
  padding: 20px;
  background: #fff;
  text-align: center;
  border: 1px solid #eef0ef;
  border-radius: 0;
  position: relative;
  overflow: hidden;
}

.pricing h3 {
  font-weight: 400;
  margin: -20px -20px 20px -20px;
  padding: 20px 15px;
  font-size: 16px;
  font-weight: 600;
  color: #777777;
  background: #f8f8f8;
}

.pricing h4 {
  font-size: 36px;
  color: #5fcf80;
  font-weight: 600;
  font-family: "Poppins", sans-serif;
  margin-bottom: 20px;
}

.pricing h4 sup {
  font-size: 20px;
  top: -15px;
  left: -3px;
}

.pricing h4 span {
  color: #bababa;
  font-size: 16px;
  font-weight: 300;
}

.pricing ul {
  padding: 0;
  list-style: none;
  color: #444444;
  text-align: center;
  line-height: 20px;
  font-size: 14px;
}

.pricing ul li {
  padding-bottom: 16px;
}

.pricing ul i {
  color: #5fcf80;
  font-size: 18px;
  padding-right: 4px;
}

.pricing ul .na {
  color: #ccc;
  text-decoration: line-through;
}

.pricing .btn-wrap {
  margin: 20px -20px -20px -20px;
  padding: 20px 15px;
  background: #f8f8f8;
  text-align: center;
}

.pricing .btn-buy {
  background: #5fcf80;
  display: inline-block;
  padding: 8px 35px;
  border-radius: 50px;
  color: #fff;
  transition: none;
  font-size: 14px;
  font-weight: 400;
  font-family: "Raleway", sans-serif;
  font-weight: 600;
  transition: 0.3s;
}

.pricing .btn-buy:hover {
  background: #3ac162;
}

.pricing .featured h3 {
  color: #fff;
  background: #5fcf80;
}

.pricing .advanced {
  width: 200px;
  position: absolute;
  top: 18px;
  right: -68px;
  transform: rotate(45deg);
  z-index: 1;
  font-size: 14px;
  padding: 1px 0 3px 0;
  background: #5fcf80;
  color: #fff;
}

/*--------------------------------------------------------------
# Contact
--------------------------------------------------------------*/
.contact {
  padding-top: 5px;
}

.contact .info {
  width: 100%;
  background: #fff;
}

.contact .info i {
  font-size: 20px;
  color: #5fcf80;
  float: left;
  width: 44px;
  height: 44px;
  background: #ecf9f0;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
}

.contact .info h4 {
  padding: 0 0 0 60px;
  font-size: 22px;
  font-weight: 600;
  margin-bottom: 5px;
  color: #37423b;
}

.contact .info p {
  padding: 0 0 0 60px;
  margin-bottom: 0;
  font-size: 14px;
  color: #657a6d;
}

.contact .info .email,
.contact .info .phone {
  margin-top: 40px;
}

.contact .info .email:hover i,
.contact .info .address:hover i,
.contact .info .phone:hover i {
  background: #5fcf80;
  color: #fff;
}

.contact .php-email-form {
  width: 100%;
  background: #fff;
}

.contact .php-email-form .form-group {
  padding-bottom: 8px;
}

.contact .php-email-form .error-message {
  display: none;
  color: #fff;
  background: #ed3c0d;
  text-align: left;
  padding: 15px;
  font-weight: 600;
}

.contact .php-email-form .error-message br+br {
  margin-top: 25px;
}

.contact .php-email-form .sent-message {
  display: none;
  color: #fff;
  background: #18d26e;
  text-align: center;
  padding: 15px;
  font-weight: 600;
}

.contact .php-email-form .loading {
  display: none;
  background: #fff;
  text-align: center;
  padding: 15px;
}

.contact .php-email-form .loading:before {
  content: "";
  display: inline-block;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  margin: 0 10px -6px 0;
  border: 3px solid #18d26e;
  border-top-color: #eee;
  animation: animate-loading 1s linear infinite;
}

.contact .php-email-form input,
.contact .php-email-form textarea {
  border-radius: 4px;
  box-shadow: none;
  font-size: 14px;
}

.contact .php-email-form input:focus,
.contact .php-email-form textarea:focus {
  border-color: #5fcf80;
}

.contact .php-email-form input {
  height: 44px;
}

.contact .php-email-form textarea {
  padding: 10px 12px;
}

.contact .php-email-form button[type=submit] {
  background: #5fcf80;
  border: 0;
  padding: 10px 35px;
  color: #fff;
  transition: 0.4s;
  border-radius: 50px;
}

.contact .php-email-form button[type=submit]:hover {
  background: #3ac162;
}

@keyframes animate-loading {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

/*--------------------------------------------------------------
# Footer
--------------------------------------------------------------*/
#footer {
  color: #37423b;
  font-size: 14px;
  background: #eef0ef;
}

#footer .footer-top {
  padding: 60px 0 30px 0;
  background: #f9faf9;
}

#footer .footer-top .footer-contact {
  margin-bottom: 30px;
}

#footer .footer-top .footer-contact h4 {
  font-size: 22px;
  margin: 0 0 30px 0;
  padding: 2px 0 2px 0;
  line-height: 1;
  font-weight: 700;
}

#footer .footer-top .footer-contact p {
  font-size: 14px;
  line-height: 24px;
  margin-bottom: 0;
  font-family: "Raleway", sans-serif;
  color: #777777;
}

#footer .footer-top h4 {
  font-size: 16px;
  font-weight: bold;
  color: #444444;
  position: relative;
  padding-bottom: 12px;
}

#footer .footer-top .footer-links {
  margin-bottom: 30px;
}

#footer .footer-top .footer-links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

#footer .footer-top .footer-links ul i {
  padding-right: 2px;
  color: #5fcf80;
  font-size: 18px;
  line-height: 1;
}

#footer .footer-top .footer-links ul li {
  padding: 10px 0;
  display: flex;
  align-items: center;
}

#footer .footer-top .footer-links ul li:first-child {
  padding-top: 0;
}

#footer .footer-top .footer-links ul a {
  color: #777777;
  transition: 0.3s;
  display: inline-block;
  line-height: 1;
}

#footer .footer-top .footer-links ul a:hover {
  text-decoration: none;
  color: #5fcf80;
}

#footer .footer-newsletter {
  font-size: 15px;
}

#footer .footer-newsletter h4 {
  font-size: 16px;
  font-weight: bold;
  color: #444444;
  position: relative;
  padding-bottom: 12px;
}

#footer .footer-newsletter form {
  margin-top: 30px;
  background: #fff;
  padding: 6px 10px;
  position: relative;
  border-radius: 50px;
  text-align: left;
  border: 1px solid #e0e5e2;
}

#footer .footer-newsletter form input[type=email] {
  border: 0;
  padding: 4px 8px;
  width: calc(100% - 100px);
}

#footer .footer-newsletter form input[type=submit] {
  position: absolute;
  top: -1px;
  right: -1px;
  bottom: -1px;
  border: 0;
  background: none;
  font-size: 16px;
  padding: 0 20px 2px 20px;
  background: #5fcf80;
  color: #fff;
  transition: 0.3s;
  border-radius: 50px;
  box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}

#footer .footer-newsletter form input[type=submit]:hover {
  background: #3ac162;
}

#footer .credits {
  padding-top: 5px;
  font-size: 13px;
}

#footer .credits a {
  color: #3ac162;
  transition: 0.3s;
}

#footer .credits a:hover {
  color: #5fcf80;
}

#footer .social-links a {
  font-size: 18px;
  display: inline-block;
  background: #5fcf80;
  color: #fff;
  line-height: 1;
  padding: 8px 0;
  margin-right: 4px;
  border-radius: 50%;
  text-align: center;
  width: 36px;
  height: 36px;
  transition: 0.3s;
}

#footer .social-links a:hover {
  background: #3ac162;
  color: #fff;
  text-decoration: none;
}
</style>

</head>


<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>휴학원</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
				<%@ include file="/WEB-INF/view/layout/mypageAsidebar.jsp"%>

			<div class="col-md-10">

				<!-- 메인 div -->
				<div class="container mt-5">
					<h1>휴학원</h1>
					<div class="row">
						<div class="col-md-12">
							<hr>
						</div>
					</div>
					
		<!-- policy 화면 start-->
        <div class="container-fluid py-5">
        <div class="container py-5 text-center">
        <div class="row justify-content-center">
        <section class="sc-5847e39e-0 hUIgIx">
        
            <h2 class="sc-5847e39e-1 DURcH" style="text-align:center"><strong>휴학 주의사항</strong></h2>
          
            <div class="sc-5847e39e-2 ihKLTc"><div class="sc-1f3e47e0-0 eQXyZv">
                <h3 style="text-align:center">※주의사항※</h3>
            
            <p style="text-align:left">1.휴학은 일반휴학(개인사정, 기타*) 병역휴학, 질병휴학, 임신휴학(1년 이내), 출산·육아휴학(통산 3년 이내), 
                &nbsp;&nbsp;창업휴학(2년 이내), 외국유학과 어학연수 휴학(1년 이상) 등이 있다.<br>
                &nbsp;&nbsp;* 코로나19 등 부득이한 사유가 발생한 경우
            
                <br><h4>2. 휴학 신청은 해당 학기 수업일수의 2분의 1까지 가능</h4>
                &nbsp;&nbsp;&nbsp;(등록금 납부자에 해당, 미납부자는 지정된 휴학 신청 기간에만 신청 가능)하며, 휴학 기간은 통산하여 
                &nbsp;&nbsp;&nbsp;다음 학기 이내로 한다.<br>
                <br>3. 각 상품의 설명에서 취소환불 정책에 대한 별도 규정이 있을 경우, 각 상품 설명의 취소환불 정책이 본 
                &nbsp;&nbsp;&nbsp;취소환불 정책보다 우선 적용됩니다. <br></p>
                <br>- 4학기 : 의과대학 의예과, 석사과정, 박사과정, 학·석사통합과정 중 학사과정</br>
                <br>- 6학기 : 학사과정, 의학전문대학원 의학과, 치의학전문대학원 치의학과, 한의학전문대학원 한의학과,
                &nbsp;&nbsp;법학전문대학원 법학과 석사과정, 석·박사통합과정, 복합과정, 학·석사통합과정 중 석사과정</br>
                <br>- 8학기 : 학·석사 연계과정, 공과대학 건설융합학부 건축학전공</br>
                <br>- 10학기 : 학·석박사통합 연계과정, 학·석사연계과정 중 건설융합학부 건축학전공</br>
                <br>- 12학기 : 학·석박사통합 연계과정 중 건설융합학부 건축학전공</br>
                
            </div>
                <div class="sc-1f3e47e0-0 eQXyZv">
                <h3 class="sc-1f3e47e0-1 gjEwSq"></h3>
                <p style="text-align:left">
                <br>3. 다만, 질병, 임신(1년 이내), 출산·육아(통산 3년 이내), 창업(2년 이내), 병역, 외국유학과 어학연수(1년 &nbsp;&nbsp;&nbsp;이상) 등 부득이한 사유가 발생한 경우에는 예외로 한다. 
                <br>&nbsp;&nbsp;&nbsp;이 경우 종합병원 또는 전문의 진단서(질병·임신·출산 휴학), 만8세 이하 자녀의 주민등록등본 또는 &nbsp;&nbsp;&nbsp;가족관계증명서(육아휴학), 입영통지서 사본, 군복무확인서, 병적증명서 중 하나(병역휴학). 
                <br>&nbsp;&nbsp;&nbsp;법원등기부등본 또는 사업자등록증(창업휴학) 등 증빙서류를 제출해야 한다.<br>
                <br>4. 일반휴학 및 병역휴학은 인터넷으로도 신청할 수 있으며, 병역휴학은 증빙서류를 첨부해야 한다. 
                <br>&nbsp;&nbsp;&nbsp;일반휴학 후 입영하는 학생은 최소 입영 7일 전 입영통지서 사본을 소속대학 행정실에 제출하여 &nbsp;&nbsp;&nbsp;일반휴학을 병역휴학으로 변경하여야 한다.<br>
                <br>5. 휴학 중 귀향조치, 폐업, 귀국 등 휴학사유가 소멸되는 경우에는 즉시 소속대학 행정실에 신고하여야 한다.<br>
                <br>6. 등록금 납부 후 휴학원을 제출하여 허가를 받은 학생은 등록금을 복학하는 학기로 이월한다.<br>
                <br>7. 휴학생이 휴학을 연장하고자 할 때에는 추가로 휴학을 신청해야 한다. 복학 또는 휴학 신청을 하지 않는 &nbsp;&nbsp;&nbsp;경우에는 제적된다. 
                <br>&nbsp;&nbsp;&nbsp;통산 휴학 가능 학기를 초과할 때에는 휴학을 연장할 수 없다.<br></p>
            </div>

        </div>
    </div>
    </section>
    </div>
    </div>
    </div>
					<!-- policy 화면 end-->
					

	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
	document.addEventListener("DOMContentLoaded", function(){
		
	    function showAlertAndRedirect(overLeaveCount, pendingLeaveCount) {
	        if (overLeaveCount >= 3) {
	            alert("휴학 신청 횟수가 초과했습니다. 자세한 내용은 학과 사무실로 연락 부탁드립니다.");
	            window.location.href = '/'; 
	        } else if (pendingLeaveCount > 0) {
	            alert("처리중인 휴학 신청이 존재합니다. 자세한 내용은 학과 사무실로 연락 부탁드립니다.");
	            window.location.href = '/student/leaveOfAbsenceList';
	        }
	    }

	    // 페이지 로드 시 실행되는 함수
	    window.onload = function() {
	        var overLeaveCount = ${overLeaveCount}; 
	        var pendingLeaveCount = ${pendingLeaveCount};
	        showAlertAndRedirect(overLeaveCount, pendingLeaveCount); 
	    };
		
	    
	    
	    
	    
	    
	    
	    
	    

		let type = document.getElementById("type").value;
		
		/* 라디오 버튼 클릭시 휴학 type value 값 변경  */
		const radioButtons = document.querySelectorAll('input[type="radio"]');

		// 라디오 버튼에 대한 이벤트 리스너 등록
		radioButtons.forEach(function(radioButton) {
		    radioButton.addEventListener('change', function() {
		        if (this.checked) {
		            let selectedType = this.nextElementSibling.textContent.trim(); // 선택된 타입을 전역 변수에 할당
		            type = selectedType; // value 값을 선택된 라디오 버튼의 라벨로 변경
		            console.log("선택된 타입:", selectedType);
		            console.log('원래 type은 변경이 됐을까?', type); // 변경된 value 값 출력
		        }
		    });
		});
		
		
		/* 휴학 기간 설정 */
		let startYearValue = ''; // 휴학 시작 년도 값 저장 변수
		let endYearValue = '';   // 휴학 종료 년도 값 저장 변수
		
		// 휴학 학기 기간 저장 변수
		let fromSemesterValue ='';
		let toSemesterValue ='';
		
		let date = new Date();
		let selYear = date.getFullYear();

		// 현재년도 기준으로 호출
		getYears(selYear);

		// 현재 년도를 select
		//document.getElementById('startYearBox').value = selYear;
		document.getElementById('startYearBox').value = '';
		document.getElementById('startYearBox').addEventListener('change', function(){
			startYearValue = this.value;
		});
		document.getElementById('endYearBox').addEventListener('change', function(){
			endYearValue = this.value;
		});

		function getYears(getY){
		    // 기존 option을 삭제함
		    let yearBox = document.getElementById('startYearBox');
		    yearBox.innerHTML = '';
		    let endYearBox = document.getElementById('endYearBox');
		    endYearBox.innerHTML = '';

		    // 올해 기준으로시작, +3년 까지보여줌
		    let stY = Number(getY);
		    let enY = Number(getY) + 3;
		    
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '년도 선택';
		    yearBox.appendChild(defaultOption.cloneNode(true));
		    endYearBox.appendChild(defaultOption.cloneNode(true));


		    for(let y = stY; y <= enY; y++){
		        let option = document.createElement('option');
		        option.value = y;
		        option.textContent = y + '년';
		        yearBox.appendChild(option);
		        

		        endYearBox.appendChild(option.cloneNode(true));
		    }
		    
		    yearBox.addEventListener('change', function() {
		    	startYearValue = this.value;
		        console.log(startYearValue);
		    	// 휴학 종료일 시작을 휴학시작년도 이후로 불러오는 함수
		    	startSemester();
		    });
		}
		
		
		function chageYear(){
			// endYearsBox 년도를 startYearsBox 년도로 select
			getYears2(document.getElementById('startYearBox').value);
			
			function getYears2(startYear) {
			    // 기존 option을 삭제함
			    let yearBox = document.getElementById('endYearBox');
			    yearBox.innerHTML = '';

			    // startYear를 기준으로 시작, +3년 까지 보여줌
			    let stY = Number(startYear);
			    let enY = Number(startYear) + 3;

			    for(let y = stY; y <= enY; y++) {
			        let option = document.createElement('option');
			        option.value = y;
			        option.textContent = y +"년";
			        yearBox.appendChild(option);
			    }
			    
			    yearBox.addEventListener('change', function() {
			    	endYearValue = this.value;
			        console.log(endYearValue);
			        
			    	// 휴학 종료일 시작을 휴학시작년도 이후로 불러오는 함수
			    	endSemester();
			    });
			}
		}
		


 
		function startSemester() {
		    let fromSemesterBox = document.getElementById('fromSemester');
		    fromSemesterBox.innerHTML = ''; // 기존의 옵션을 삭제합니다.

		    // "학기" 옵션을 생성합니다.
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '휴학 신청 학기';
		    fromSemesterBox.appendChild(defaultOption.cloneNode(true));

		    // "1학기"와 "2학기"를 옵션으로 추가합니다.
		    let option1 = document.createElement('option');
		    option1.value = '1';
		    option1.textContent = '1학기';
		    fromSemesterBox.appendChild(option1);

		    let option2 = document.createElement('option');
		    option2.value = '2';
		    option2.textContent = '2학기';
		    fromSemesterBox.appendChild(option2);

		    // 학기가 선택될 때 값을 저장합니다.
		    fromSemesterBox.addEventListener('change', function(){
		    	fromSemesterValue = this.value;
		        console.log(fromSemesterValue);
		        
		    	chageYear();
		    });
		}
		

		function endSemester() {
		    let toSemesterBox = document.getElementById('toSemester');
		    toSemesterBox.innerHTML = ''; // 기존의 옵션을 삭제합니다.

		    // "학기" 옵션을 생성합니다.
		    let defaultOption = document.createElement('option');
		    defaultOption.value = '';
		    defaultOption.textContent = '휴학 신청 학기';
		    toSemesterBox.appendChild(defaultOption.cloneNode(true));


		 // 시작년도와 종료년도가 같고 시작학기가 2학기인 경우
		    if (startYearValue === endYearValue && fromSemesterValue === '2') {
		        // "2학기" 옵션만 추가합니다.
		        let option2 = document.createElement('option');
		        option2.value = '2';
		        option2.textContent = '2학기';
		        toSemesterBox.appendChild(option2);
		    } else {
		        // 그 외의 경우에는 "1학기"와 "2학기" 옵션을 모두 추가합니다.
		        let option1 = document.createElement('option');
		        option1.value = '1';
		        option1.textContent = '1학기';
		        toSemesterBox.appendChild(option1);

		        let option2 = document.createElement('option');
		        option2.value = '2';
		        option2.textContent = '2학기';
		        toSemesterBox.appendChild(option2);
		    }
		 
		    // 학기가 선택될 때 값을 저장합니다.
		    toSemesterBox.addEventListener('change', function(){
		    	
		        toSemesterValue = this.value;
		        console.log(toSemesterValue);
		    });
		}
		
		
		
		
		
		/* 휴학 정보 전송 */
		const loaBtn = document.getElementById("loa-btn");
		
		loaBtn.addEventListener("click", async function(event){
			/*if(!confirm("휴학 신청을 하시겠습니까?")){
				event.preventDefault();
				return;
			}*/

			let studentId = document.getElementById("studentId").value;
			let studentGrade = document.getElementById("studentGrade").value;
			let appDate = document.getElementById("appDate").value;

			console.log("studentId : " + studentId);
			console.log("studentGrade : " + studentGrade);
			console.log("startYearValue" + startYearValue);
			console.log("endYearValue" + endYearValue);
			console.log("fromSemesterValue"+fromSemesterValue);
			console.log("toSemesterValue"+toSemesterValue);
			console.log("type : " + type);
			console.log("appDate : " + appDate);
			
	        let formData = {
	                "studentId": studentId,
	                "studentGrade": studentGrade,
	                "fromYear" : startYearValue,
	                "fromSemester" : fromSemesterValue,
	                "toYear": endYearValue,
	                "toSemester" : toSemesterValue,
	                "type" : type,
	                "appDate": appDate
	            };
	        
	        try {
	            const response = await fetch('/student/leaveApp', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                body: JSON.stringify(formData)
	            });

	            if (!response.ok) {
	            	const errorMessage = await response.text();
	                throw new Error(errorMessage);
	            }

	            // 서버 응답이 성공적으로 도착한 경우
	            alert('휴학 신청이 완료되었습니다.');
	            window.location.href = '/student/leaveOfAbsenceList';

	        } catch (error) {
	            // 오류가 발생한 경우
	            alert(error.message);
	        }
		});	// loaBtn.addEventListener end

	});
	</script>
</body>
</html>
