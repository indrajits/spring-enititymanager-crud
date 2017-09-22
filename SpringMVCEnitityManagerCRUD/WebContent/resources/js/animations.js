// HELLO
function hello(){
	$('.hello').delay(2000).addClass('animated fadeInDown');
	setTimeout(function(){$(".hello h1").addClass('animated fadeInUp');}, 600);
	setTimeout(function(){$(".btn").addClass('animated fadeInDown');}, 600);
	}
	
function soon(){
	$('.soon h2').delay(1500).addClass('animated fadeInUp');
	
	}	
	
	function align(){
	$('.align').delay(500).addClass('animated fadeInLeft');
	
	}	
	function image(){
	$('.right').delay(250).addClass('animated fadeInRight');
	
	}	
	function slider(){
	$('.owl-carousel').delay(250).addClass('animated fadeInDown');
	
	}	
	
	
	
$(window).load(function() { // makes sure the whole site is loaded
	$('#pong').delay(1000).fadeOut(); // pong fade out
	$('.loading').delay(1000).fadeOut('slow', function(){ // loading fade out
		hello();
		$('.owl-carousel').owlCarousel({
    loop:true,
    margin:0,
    responsiveClass:true,
    responsive:{
        0:{
            items:1,
            nav:false
        },
        500:{
            items:1,
            nav:false
        },
        800:{
            items:2,
            nav:false,
            loop:false
        },
        1500:{
            items:3,
            nav:false,
            loop:false
        }
    }
})
	});								
})


 $(document).ready(function(){
	 

	  
	  onePageScroll(".main", {
    afterMove: function (index) {
       if ($('.page3').hasClass('active')) {
		slider();
		soon();
		
      }
	  if ($('.page2').hasClass('active')) {
		align();
		image();
      }
    }
  });
});
  
		