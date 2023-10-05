import { loadSmplrJs } from "@smplrspace/smplr-loader";
import { Desk, Servers, AC, Departments } from './data001.js';


let points = [];

//
//loadSmplrJs("umd")
//	.then((smplr) => {
//		//////////////////////////////
//
//		const space = new smplr.Space({
//			spaceId: "b6e6acf9-1524-4f2b-a257-c6edd64832e0",
//			clientToken: "pub_fc4b7f5e33bd49cf98912c56c404de8c",
//			containerId: "container",
//		});
//		////////////////////////////////////
//		//Start View 
//		space.startViewer({
//			preview: true,
//			onReady: () => console.log("Viewer is ready"),
//			onError: (error) => console.error("Could not start viewer", error),
//		});
//
//		////////////////////////////////////
//		//Remove Dep Layer 
//		window.remove = function() {
//			space.removeDataLayer("Departments");
//			console.log("Dep removed");
//		}
//		//////////////////////////////////////
//
//
//		window.updateLayer = () => {
//			updateDataLayers();
//			console.log("Update function is called!")
//		}
//		/////////////////////////////
//		//Update Data
//		function updateDataLayers() {
//			space.removeDataLayer('IT_Room');
//
//			space.addDataLayer({
//				id: "Desk",
//				type: 'furniture',
//				data: Desk,
//				tooltip: (d) => `${d.name} - ${d.Employee == '' ? 'free' : d.Employee}`,
//				color: (d) => (d.Employee == '' ? '#f75e56' : '#03fc24'),
//			});
//
//
//			space.addDataLayer({
//				id: "Servers",
//				type: 'furniture',
//				data: Servers,
//				tooltip: (d) => `${d.name} - ${d.Brand} -${d.temprature}! ${d.temprature > 65 ? 'Hot Temp' : 'Normal Temp'}`,
//				color: (d) => `${d.temprature >= 65 ? '#f75e56' : '#50b268'}`,
//			});
//
//			space.addDataLayer({
//				id: "AC",
//				type: 'furniture',
//				data: AC,
//				tooltip: (d) => `${d.name} - ${d.Brand}`,
//				color: (d) => (d.ON ? '#0398fc' : '#f75e56'),
//			});
//
//			space.addDataLayer({
//				id: "Departments",
//				type: 'polygon',
//				data: Departments,
//				baseHeight: 0,
//				height: 1.5,
//				color: (d) => (d.name == 'CEO' ? '#0398fc' : (d.name == 'Seniors' ? '#5feb63' : '#d15c5c')),
//				alpha: 0.5,
//				tooltip: (d) => `Dep: ${d.name}`,
//			})
//
//			console.log(space.getDataLayer("Departments"));
//
//		};
//		/////////////////////////////////////////
//		////////////////////////////
//	})

// Generate Id ==> It will be replaced by the UUID$ package
function generateSpecificID() {
	const randomID = Math.floor(Math.random() * (1000 - 9999 + 1)) + 9999;
	console.log(randomID);
	return randomID;
}

