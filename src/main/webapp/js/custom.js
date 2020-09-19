//// Datatables plugin
//$(document).ready( function () {
//    $('#table').DataTable();
//} );

// Function to ask the user for delete confirmation
function confirmDelete(name) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to delete the course ' + name + ' ?')) {

	} else {
		event.preventDefault();
	}
}

// Function to ask the user for course enroll confirmation
function confirmEnroll(name) {

	// The confirmEnroll() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to enroll on this course ' + name + ' ?')) {

	} else {
		event.preventDefault();
	}
}

// Function to ask the user for course enroll confirmation
function confirmLeave(name) {

	// The confirmLeave() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to leave this course ' + name + ' ?')) {

	} else {
		event.preventDefault();
	}
}