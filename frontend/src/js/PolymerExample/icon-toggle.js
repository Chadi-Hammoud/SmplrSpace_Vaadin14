import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/iron-icon/iron-icon.js';

// Define the element's API using an ES2015 class
class XCustom extends PolymerElement {

	// Define optional shadow DOM template
	static get template() {
		return html`
		<style>
		  :host {
		    display: inline-block;
		  }
		  iron-icon {
		    fill: var(--icon-toggle-color, rgba(0,0,0,0));
		    stroke: var(--icon-toggle-outline-color, currentcolor);
		  }
		  :host([pressed]) iron-icon {
		    fill: var(--icon-toggle-pressed-color, currentcolor);
		  }
		</style>
  
      <!-- shadow DOM goes here -->
      <!--  <span>Not much here yet.</span>-->
     <!-- <iron-icon icon="polymer"></iron-icon>-->
     <iron-icon icon="[[toggleIcon]]"></iron-icon>

    `;
	}
	// Declare properties for the element's public API
	static get properties() {
		return {
			toggleIcon: {
				type: String
			},
			pressed: {
				type: Boolean,
				value: false,
				notify: true,
				reflectToAttribute: true
			},
		};
	}

	constructor() {
		super();
		this.addEventListener('click', this.toggle.bind(this));
	}

	toggle() {
		this.pressed = !this.pressed;
	}


}

// Register the x-custom element with the browser
customElements.define('icon-toggle', XCustom);
