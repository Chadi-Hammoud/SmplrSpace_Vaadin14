import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';
import { loadSmplrJs } from "@smplrspace/smplr-loader";



class SmplrSpaceContainer extends PolymerElement {

	static get template() {
		return html`

			<!-- This is the div the Space will be rendered in -->
			<div id="container"></div>
        `;
	}


	static get is() {
		return "smplrspace-container";
	}



	/**
		 * The default for Polymer is to create a shadow root for each element. In this case, touch events will 
		 * conflict with Polymer helpers so we use regular (light) DOM instead.
		 */
	_attachDom(dom) {
		this.appendChild(dom); // don't create shadow root, just append the content
		return this;
	}

	/**
     * Again, we can't use Shadow DOM because it conflicts (see above). Here we check if this element is 
     * used inside a shadow root without a <slot>.
     */
	connectedCallback() {
		super.connectedCallback();

		var element = this;
		while (element.parentNode && (element = element.parentNode)) {
			if (element instanceof ShadowRoot) {
				console.error("This element does not support shadow roots. Please use a <slot> for the element instead.", element);
			}
		}
	}

	/**
		 * Called automatically when this element is created
		 */
	ready() {
		super.ready();

		this.loadSmplrJs = loadSmplrJs("umd")
			.then((smplr) => {
				/* enjoy a fully typed API and auto-completion */
				const space = new smplr.Space({
					spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
					clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
					containerId: "container",
				});

				const startView = () =>
					space.startViewer({
						preview: true,
						loadingMessage: "Hello From Mobinets Workspace",
						mode: '3d',
						allowModeChange: true,
						// onModeChange: ( '2d'),
						onReady: () => {
							console.log("===> View IS READY");
							console.log("===>Calling UpdateDataLayers()");
							updateDataLayers();
							callOnClick();
							console.log("===>After calling UpdateDataLayers()");
						},
						onError: (error) => console.error("Could not start viewer", error)

					});

				startView();

			});
	}

}
customElements.define(SmplrSpaceContainer.is, SmplrSpaceContainer)

































