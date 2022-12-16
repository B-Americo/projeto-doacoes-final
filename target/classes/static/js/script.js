//FOTOS CARREGAR

//document.getElementById('files').onchange = function () {
  //          var src = URL.createObjectURL(this.files[0])
  //          document.getElementById('image').src = src
  //          }


//*********CADASTRO DONATE*************//


//*MODAL CARREGAR FOTOS




//* FIM MODAL CARREGAR FOTOS

//GRUPO

function b1() {
      document.getElementById('grupo').value = "Donate----";
      
        };


//FIM DO GRUPO
var profilePic = null;

function previewImagem() {
  var imagem = document.querySelector('input[name=imagemDonate]').files[0];
  var preview = document.querySelector('img[name=imagemPadrao]');
  
  //Guardando a imagem inicial
  if(profilePic == null)
    profilePic = preview.src;

  var reader = new FileReader();

  reader.onloadend = function() {
    preview.src = reader.result;
  }

  if (imagem) {
    reader.readAsDataURL(imagem);
  } else {
    preview.src = "";
  }
}

function restoreOriginal(){
  var preview = document.querySelector('img[name=imagemPadrao]');
  
  //Limpando a seleção do arquivo
  document.querySelector('input[name=imagemDonate').value = "";
  
  //Restaurando a imagem inicial
  if(profilePic != null)
    preview.src = profilePic
    
}



//GERAR ENDEREÇO DOS USUARIOS///

function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('uf').value=("");
            document.getElementById('ibge').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
            document.getElementById('ibge').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('uf').value="...";
                

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }






}

//FIM DO GERAR ENDEREÇO DOS USUARIOS///

//CADASTRAR NOVO USUÁRIO

function cadastro(){
  alert('Cadastro realizado com sucesso');
}