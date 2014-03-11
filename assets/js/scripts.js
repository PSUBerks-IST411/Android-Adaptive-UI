var clock;

$(document).ready(function() {

var currentDate = new Date();
	// Set some date in the future. In this case, it's always Jan 1
	var futureDate  = new Date("May 05, 2014 01:15:00");
	// Calculate the difference in seconds between the future and current date
	var diff = futureDate.getTime() / 1000 - currentDate.getTime() / 1000;
	// Instantiate a countdown FlipClock
	
	clock = $('.dw_clock').FlipClock(diff, {
		clockFace: 'DailyCounter',
		countdown: true,
		showSeconds: true
	});
});