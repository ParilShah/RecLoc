//
//  RLPlacesCollectionViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 2/21/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import MapKit
import Pulley
import NVActivityIndicatorView

private let reuseIdentifier = "PlacesCell"

class RLPlacesCollectionViewController: UICollectionViewController {
    var items = [Any]()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Uncomment the following line to preserve selection between presentations
        self.clearsSelectionOnViewWillAppear = false
        // Call API for fetching all locations.
        fetchAllLocations()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - CUSTOM METHODS
    func fetchAllLocations(){
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        let rlPlacesVM = RLPlacesVM.init(urlString: Constant.baseURL + "location/fetchLocations", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Location]
            self.collectionView?.reloadData()
            activityIndicator.stopAnimating()
        })
        rlPlacesVM.fetchLocationsByCategoriesIds(with: "")
    }
}


extension RLPlacesCollectionViewController {

    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
    {
        return self.items.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! RLPlaceCollectionViewCell
        var location = items[indexPath.row] as! Location
        cell.textLabel?.text = location.locationName
        let image: UIImage?
        if (location.locationPhoto == nil){
            image = convertDataToImage(withData: location)
            location.locationPhoto = image
            items[indexPath.row] = location
        }else{
            image = location.locationPhoto
        }
        cell.imageView?.image = image
        return cell
    }
    
    func convertDataToImage(withData location:Location) -> UIImage{
        var locationImage:UIImage?
        let locationImageDataString:String = location.photoBytes!
        let locationImageData = NSData(base64Encoded: locationImageDataString, options:NSData.Base64DecodingOptions.init(rawValue: 0))
        locationImage = UIImage(data:locationImageData! as Data,scale:1.0)!
        return locationImage!
    }
    
    public override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let mainContentVC = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "PrimaryContentViewController") as! RLMapViewController
        let drawerContentVC = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "DrawerContentViewController") as! RLLocationInfoViewController
        mainContentVC.location = items[indexPath.row] as? Location
        drawerContentVC.location = items[indexPath.row] as? Location
        drawerContentVC.locationImage = ((items[indexPath.row] as? Location)?.locationPhoto)!
        let pulleyDrawerVC = PulleyViewController(contentViewController: mainContentVC, drawerViewController: drawerContentVC)
        
        pulleyDrawerVC.initialDrawerPosition = .collapsed
        self.present(pulleyDrawerVC, animated: true, completion: nil)
    }

}
