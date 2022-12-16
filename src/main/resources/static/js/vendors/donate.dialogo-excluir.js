Donate = Donate || {};


Donate.DialogoExcluir = (function(){
	
	function DialogoExcluir(){
		this.exclusaoBtn = $('.js-exclusao-btn')
	}
	
	DialogoExcluir.prototype.iniciar = function(){
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
	}
	
	function onExcluirClicado(evento){
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		
		
		
		swalWithBootstrapButtons.fire({
		  title: 'Are you sure?',
		  text: "You won't be able to revert this!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Yes, delete it!',
		  cancelButtonText: 'No, cancel!',
		  reverseButtons: true
		}, onExcluirConfirmado.bind(this, url));
		
		
	
	}
	
	function onExcluirConfirmado(url){
		console.log('url', url);
	}
	
	return DialogoExcluir;
	
}());


$(function(){
	var dialogo = new Donate.DialogoExcluir();
	dialogo.iniciar();
});