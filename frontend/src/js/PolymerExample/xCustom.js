import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
class XCustom extends PolymerElement {

	static get properties() {
		return {
			user: String,
			manager: {
				type: Boolean,
				notify: true
			},
			mode: {
				type: String,
				value: 'auto'
			},
			data: {
				type: Object,
				notify: true,
				value: function() { return {}; }
			},
			response: {
				type: Object,
				readOnly: true,
				notify: true
			},
		}
	}

	constructor() {
		super();
		this.mode = 'auto';
		this.data = {};
	}

	connectedCallback() {
		super.connectedCallback();

		// render
		this.textContent = 'Hello ' + (this.user || 'private user') + ', my user is ' + (this.user || 'nobody') + '.\n' +
			'This user is ' + (this.manager ? '' : 'not') + ' a manager.';
	}

	responseHandler(response) {
		// set read-only property
		this._setResponse(response);
	}



}

customElements.define('x-custom', XCustom);