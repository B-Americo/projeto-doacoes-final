var Donate = Donate || {};

Donate.ComboEstado = (function(){
	
	function ComboEstado(){
		this.combo = $('#estado')
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		
	}
	
	ComboEstado.prototype.iniciar = function(){
		this.combo.on('change', onEstadoAlterado.bind(this))
		console.log('estado selecionado', this.combo.val());
	}
	
	function onEstadoAlterado(){
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	
	return ComboEstado;
	
	
}());

Donate.ComboCidade = (function(){
	
	function ComboCidade(comboEstado){
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
	}
	
	ComboCidade.prototype.iniciar = function(){
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
		
	}
	
	function onEstadoAlterado(evento, codigoEstado){
		if(codigoEstado){
			
		
	var resposta = $.ajax({
					url: this.combo.data('url'),
					method: 'GET',
					contentType: 'application/json',
					data: {'estado': codigoEstado}
		});
		
		resposta.done(onBuscarCidadesFinalizado.bind(this));
	}
	}
	
	function onBuscarCidadesFinalizado(cidade){
		var options = [];
		cidade.forEach(function(cidad){
			options.push('<option value"' + cidad.id + '">' + cidad.nome + '</option>');
			
			
		});
		
		this.combo.html(options.join(''));
	}
	
	
	
	return ComboCidade;
	
	
	
}());



$(function(){
	var comboEstado = new Donate.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Donate.ComboCidade(comboEstado);
	comboCidade.iniciar();
});