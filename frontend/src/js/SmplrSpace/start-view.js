import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
//import '@polymer/paper-input/paper-input.js';
import { Smplr } from '@smplrspace/smplr-loader';

class SmplrView extends PolymerElement {

	static get template() {
		return html`
		<button on-click="handleClick">Say hello</button>
		<button on-click="sayHi">Say Hi</button>
		<div id="container"></div>`;
	}

	static get is() {
		return 'smplr-space';
	}
}
customElements.define(SmplrView.is, SmplrView);