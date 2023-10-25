import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';
import { loadSmplrJs } from "@smplrspace/smplr-loader";
import { Desk, Servers, AC, Departments } from './data001.js';


class SmplrSpaceContainer extends PolymerElement {


	static get properties() {
		return {
			ss: {
				type: Object,
			},
			spaceId: {
				type: String,
			},
			clientToken: {
				type: String,
			},
			containerId: {
				type: String,
			},
			pointList: {
				type: Array,
				notify: true,
			},
			tempPoint: {
				type: Object,
			},
			coordinates: {
				elevation: {
					type: Number,
				},
				levelIndex: {
					type: Number,
				},

				x: {
					type: Number,
				},
				z: {
					type: Number,
				},
			},
			point: {
				type: Array,
				id: {
					type: String,
				},
				name: {
					type: String,
				},
				position: {
					elevation: {
						type: Number,
					},
					levelIndex: {
						type: Number,
					},

					x: {
						type: Number,
					},
					z: {
						type: Number,
					},
				}
			},
		}
	}

	static get template() {
		return html`
		<style>
		div{
			width: 800px;
			height: 500px;
		}
		</style>
		<div id="container"></div>`;
	}

	_attachDom(dom) {
		this.appendChild(dom);
		return this;
	}

	constructor() {
		super();
		this.pointList = [];
	}

	static get is() {
		return "smplrspace-container";
	}

	ready() {
		super.ready();
	}

	connectedCallback() {
		super.connectedCallback();

		var element = this;
		while (element.parentNode && (element = element.parentNode)) {
			if (element instanceof ShadowRoot) {
				console.error("This element does not support shadow roots. Please use a <slot> for the element instead.", element);
			}
		}

		loadSmplrJs("umd")
			.then((smplr) => {
				console.log("smplr" + JSON.stringify(smplr))
				this.ss = new smplr.Space({
					spaceId: JSON.parse(this.spaceId),
					clientToken: JSON.parse(this.clientToken),
					containerId: JSON.parse(this.containerId),
				});
				console.log("In after the construct: " + this.ss)
				this.startSpaceView();

			});
	}

	disconnectedCallback() {
		this.ss.remove();
	}

	callSpace() {
		/*console.log(this.ss)*/
	}

	startSpaceView() {
		this.ss.startViewer({
			preview: true,
			onReady: () => {
				console.log("Viewer is ready");
			},
			onError: (error) => console.error("Could not start viewer", error),
		});

	}

	///////////////////////////////////////

	addPointDataJava() {
		this.ss.addDataLayer({
			id: 'point',
			type: 'point',
			data: JSON.parse(this.pointList),
			diameter: 0.5,
			anchor: 'bottom',
			tooltip: d => d.id,
			onClick: (data) => {
				this.tempPoint = data;
				this.getClickedPoint(data._id);
			},
			onDrop: ({ data, position }) => {
				this.coordinates = position;
				this.getClientUpdatedData(data._id);
				this.tempPoint = data;
			}
		})
	}




	disablePick() {
		this.ss.disablePickingMode();
	}

	enablePickingMode() {
		this.ss.enablePickingMode({
			onPick: ({ coordinates }) => {
				this.coordinates = coordinates;
				this.getClientData();

			}
		})
	}

	generateSpecificID() {
		const randomID = Math.floor(Math.random() * (1000 - 9999 + 1)) + 9999;
		console.log(randomID);
		return randomID;
	}

	updateView() {
		this.ss.updateDataLayer({
			id: "point",
			type: 'point',
			data: JSON.parse(this.pointList),
			diameter: 0.5,
			anchor: 'bottom',
			tooltip: d => d.id,
			onClick: (data) => {
				this.tempPoint = data;
				this.getClickedPoint(data._id);
				console.log(this.pointList);
			},
			onDrop: ({ data, position }) => {
				this.coordinates = position;
				this.getClientUpdatedData(data._id);
				this.getClientData();
				this.tempPoint = data;
			}
		})
	}

	importView() {
		this.ss.addDataLayer({
			id: "point",
			type: 'point',
			data: JSON.parse(this.pointList),
			diameter: 0.5,
			anchor: 'bottom',
			tooltip: d => d.id,
			onClick: (data) => {
				this.tempPoint = data;
				this.getClickedPoint(data._id);
				console.log(this.pointList);
			},
			onDrop: ({ data, position }) => {
				this.coordinates = position;
				this.getClientUpdatedData(data._id);
				this.getClientData();
				this.tempPoint = data;
			}
		})
	}	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////

	getClientData() {
		this.$server.setClientData(this.coordinates.x, this.coordinates.z, this.coordinates.levelIndex, this.coordinates.elevation);

	}

	getClientUpdatedData(id) {
		let _pointID = id;
		let _newCoordinates = this.coordinates;
		let updates = {
			id: _pointID,
			updates: _newCoordinates,
		}
		var jsonData = JSON.stringify(updates);
		this.$server.setClientUpdatedData(jsonData);
	}

	getClickedPoint(_clickedPointID) {
		this.$server.getClickedPoint(_clickedPointID);
	}
	
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	updateDataLayers() {
		this.ss.removeDataLayer('IT_Room');
		this.ss.addDataLayer({
			id: "Desk",
			type: 'furniture',
			data: Desk,
			tooltip: (d) => `${d.name} - ${d.Employee == '' ? 'free' : d.Employee}`,
			color: (d) => (d.Employee == '' ? '#f75e56' : '#03fc24'),
		});
		this.ss.addDataLayer({
			id: "Servers",
			type: 'furniture',
			data: Servers,
			tooltip: (d) => `${d.name} - ${d.Brand} -${d.temprature}! ${d.temprature > 65 ? 'Hot Temp' : 'Normal Temp'}`,
			color: (d) => `${d.temprature >= 65 ? '#f75e56' : '#50b268'}`,
		});
		this.ss.addDataLayer({
			id: "AC",
			type: 'furniture',
			data: AC,
			tooltip: (d) => `${d.name} - ${d.Brand}`,
			color: (d) => (d.ON ? '#0398fc' : '#f75e56'),
		});
		this.ss.addDataLayer({
			id: "Departments",
			type: 'polygon',
			data: Departments,
			baseHeight: 0,
			height: 1.5,
			color: (d) => (d.name == 'CEO' ? '#0398fc' : (d.name == 'Seniors' ? '#5feb63' : '#d15c5c')),
			alpha: 0.5,
			tooltip: (d) => `Dep: ${d.name}`,
		})
	}
}
customElements.define(SmplrSpaceContainer.is, SmplrSpaceContainer)