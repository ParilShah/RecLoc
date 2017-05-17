//
//  RLMapViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 5/8/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Pulley
import GoogleMaps

class RLMapViewController: UIViewController {
    
    public var location:Location?
    @IBOutlet weak var mapView:UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        let latitude = Double((location?.address?.latitude)!)
        let longitude = Double((location?.address?.longitude)!)
        
        let camera = GMSCameraPosition.camera(withLatitude:latitude!, longitude:longitude!, zoom: 6.0)
        let googleMap = GMSMapView.map(withFrame: self.view.bounds, camera: camera)
        self.mapView?.addSubview(googleMap)
        
        // Creates a marker in the center of the map.
        let marker = GMSMarker()
        marker.position = CLLocationCoordinate2D(latitude:latitude!, longitude: longitude!)
        marker.title = location?.locationName
        marker.snippet = location?.address?.country
        marker.icon = UIImage(named:"flat-marker")
        marker.appearAnimation = GMSMarkerAnimation.pop
        marker.map = googleMap
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // Reset to Original Position
    @IBAction func moveToOriginalLocation(_ sender: Any) {
        
        let latitude = Double((self.location?.address?.latitude)!)
        let longitude = Double((self.location?.address?.longitude)!)

        let location = GMSCameraPosition.camera(withLatitude: latitude!,
                                              longitude: longitude!,
                                              zoom: 6)
        (mapView.subviews.first as! GMSMapView).camera = location
    }
    
    @IBAction func pressClose(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }

}

extension RLMapViewController: PulleyPrimaryContentControllerDelegate {
    
    func makeUIAdjustmentsForFullscreen(progress: CGFloat) {
//        controlsContainer.alpha = 1.0 - progress
    }
    
    func drawerChangedDistanceFromBottom(drawer: PulleyViewController, distance: CGFloat) {
        if distance <= 268.0 {
//            temperatureLabelBottomConstraint.constant = distance + temperatureLabelBottomDistance
        } else {
//            temperatureLabelBottomConstraint.constant = 268.0 + temperatureLabelBottomDistance
        }
    }
}
