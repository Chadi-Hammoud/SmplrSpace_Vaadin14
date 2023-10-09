import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/iron-icons/iron-icons.js';
import './icon-toggle.js';
import './xCustom.js';

class DemoElement extends PolymerElement {

	static get properties() {
		return {
			inputValue: {
				type: String,
				value: "		",
				textContent: "Hello Ibrahim ",
				notify: true,
			},
			txtInput: {
				type: String,
				value: "id001",
				notify: true,
			},
			name: {
				//				type: Object,
				value: {
					first: "ABD",
					last: "test001",
				},
			}
		}
	}



	constructor() {
		super();
		/* set this element's owner property */
		this.owner = 'Daniel';
		//		this.txtinput = '[constructor]: text Input by Chadi';
		//		this.name = { first: 'Chadi', last: 'Hammoud' };
		this.name.middle = 'Chadi';
	}




	static get template() {
		return html`
      <style>
        :host {
          font-family: sans-serif;
          --icon-toggle-color: lightgrey;
          --icon-toggle-outline-color: black;
          --icon-toggle-pressed-color: red ;

			margin-left: 20px;
        }
		div{
			  width: auto;
		      height: auto;
		      background-color: #3498db;
		      color: #ffffff;
		      text-align: center;
		      padding: 20px;
		      margin: 20px;
		      border: 2px solid #2980b9;
		      border-radius: 5px;
		}
		.container{
			margin-left:20px;
			background: none;	
		}
      </style>
<div class="container">
      	<h3>Statically-configured icon-toggles</h3>
      	<icon-toggle toggle-icon="star"></icon-toggle>
      	<icon-toggle toggle-icon="star" pressed></icon-toggle>
    
      	<h3>Data-bound icon-toggle</h3>
      <!-- use a computed binding to generate the message -->
      	<div><span>[[_message(isFav)]]</span>
			<!-- curly brackets ({{}}} allow two-way binding --> 
     		 <icon-tgggle ibrahim-dib-abc="favorite" pressed="{{isFav}}"></icon-tgggle>
		</div>
 	  <!-- bind to the "owner" property -->
		<input type= "text" id ={{txtInput}} value={{inputValue}} />
		<button id="btn" on-click="handleClick">clickMe</button>
    	<p>  This is <b>{{owner}}</b>'s name-tag element.</p>
		<button id="btn1" on-click="handleClick">Kick Me</button>
		 <div>[[name.first]] [[name.middle]] [[name.last]]</div>
		<div><x-custom ></x-custom></div>
		<div><x-custom user="Chadi" manager></x-custom></div>
</div>
    `;
	}

	ready() {
		super.ready();
		const shadowRoot = this.shadowRoot;
		console.log("shadow Root: " + shadowRoot)
		console.log(this.txtInput)
		const element = shadowRoot.querySelector("#" + this.txtInput); 
		console.log("element: " + element);

		if (element) {
			console.log("element: " + element.value);
			//			// You can now work with 'element'
			//			console.log(this.txtInput);
		} else {
			console.error('Element ' + this.txtInput + ' not found in shadow DOM.');
		}

	}

	connectedCallback() {
		super.connectedCallback();

		//		const inputElement = this.$.inputValue;
		//		if (inputElement) {
		//			console.log(this.$.inputElement.textContent);
		//		} else {
		//			console.error('Element with ID "inputt" not found.');
		//		}
	}

	handleClick() {
		console.log('Ow!');
		//		this.connectedCallback();
		//		const shadowRoot = this.shadowRoot;
		//		console.log("shadow Root: " + shadowRoot);
		//		//		const inputElement = this.$.getInputValue;
		//		const element = shadowRoot.querySelector("#" + this.txtInput); // Replace 'yourElementId' with the actual ID
		//
		//		//		this.inputValue.value = this.name.first + " "+ this.name.last;	
		//
		//
		//		//		if (element) {
		//		console.log(element);
		//		//			// You can now work with 'element'
		//		//			console.log(this.txtinput);
		//		//		} else {
		//		//			console.error('Element ' + this.txtinput + ' not found in shadow DOM.');
		//		//		}
		//
		//
		//		//		
		this.ready();


	}


	_message(fav) {
		if (fav) {
			return 'You really like me!';
		}
		else {
			return 'Do you like me?';
		}
	}
}
customElements.define('demo-element', DemoElement);
