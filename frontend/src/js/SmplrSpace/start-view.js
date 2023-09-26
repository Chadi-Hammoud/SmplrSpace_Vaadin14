import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
//import '@polymer/paper-input/paper-input.js';
import { Smplr } from '@smplrspace/smplr-loader';

class SmplrView extends PolymerElement {

	static get template() {
		return html`
			<div id="container"></div>`;
	}

	static get is() {
		return 'smplr-space';
	}
	//	handleClick() {
	//		console.log('Button was clicked.');
	//		window.alert('Hello');
	//	}
}

function sayHi() {
	console.log('hi');
}


//const startView = ()=>{

function startView() {
	const space = new smplr.Space({
		spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
		clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
		containerId: "container"
	});

	space.startViewer({
		preview: true,
		loadingMessage: "Hello From Mobinets Workspace",
		mode: '3d',
		allowModeChange: true,
		// onModeChange: ( '2d'),
		onReady: () => {
			console.log("===>Calling UpdateDataLayers()");
			updateDataLayers();
			callOnClick();
			console.log("===> View IS READY");
		},
		onError: (error) => console.error("Could not start viewer", error),
	});
}



customElements.define(SmplrView.is, SmplrView);