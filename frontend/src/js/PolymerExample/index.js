import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/iron-icon/iron-icon.js';
import './icon-toggle.js';
import './demo-element.js';

// Define the element's API using an ES2015 class
class IndexDemo extends PolymerElement {

	// Define optional shadow DOM template
	static get template() {
		return html`
		
		<demo-element></demo-element>
	

`;
	}

}
// Register the x-custom element with the browser
customElements.define('icon-index', IndexDemo);
