import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/iron-icon/iron-icon.js';
import './icon-toggle.js';
import './demo-element.js';

// Define the element's API using an ES2015 class
class IndexDemo002 extends PolymerElement {

	// Define optional shadow DOM template
	static get template() {
		return html`
		
			<icon-toggle toggle-icon="favorite" pressed="{{isFav}}"></icon-toggle>
			<icon-toggle toggle-icon="favorite" pressed="{{isFav}}"></icon-toggle>
		
		<icon-toggle toggle-icon="favorite" pressed="{{true}}"></icon-toggle>

`;
	}

}
// Register the x-custom element with the browser
customElements.define('icon-indexx', IndexDemo002);
