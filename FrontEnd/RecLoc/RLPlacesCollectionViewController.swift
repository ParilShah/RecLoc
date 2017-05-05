//
//  RLPlacesCollectionViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 2/21/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import MapKit

private let reuseIdentifier = "PlacesCell"

class RLPlacesCollectionViewController: UICollectionViewController {
    var items = [Any]()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let value1 = ["Name":"Japanese Forest","ImageName":"Forest","Tags":["Forest","Tree"], "Description":"This is an awesome place","lat":"-33.86", "lang":"151.20"] as [String : Any]
        
        let value2 = ["Name":"Yosemite","ImageName":"Mountain","Tags":["Mountain","Snow"], "Description":"Look is naturally awesome","lat":"37.8651", "lang":"119.5383"] as [String : Any]
        
        let value3 = ["Name":"Florida Beach","ImageName":"Beach", "Tags":["Beach","Tree","Sand"], "Description":"Awesome place for summer","lat":"28.3200", "lang":"80.6076"] as [String : Any]
        
        self.items.append(value1)
        self.items.append(value2)
        self.items.append(value3)
        
        let rlPlacesVM = RLPlacesVM.init(urlString:"http://localhost:8080/location/fetchLocationsByCategoriesById", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Categories]
        })
        rlPlacesVM.fetchLocationsByCategoriesIds()

        
        // Uncomment the following line to preserve selection between presentations
         self.clearsSelectionOnViewWillAppear = false
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
    {
        // #warning Incomplete implementation, return the number of items
        return self.items.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! RLPlaceCollectionViewCell
        // Configure the cell
        let dictionary = items[indexPath.row] as! NSDictionary
        cell.imageView?.image = UIImage(named:(dictionary["ImageName"] as? String)!)
        cell.textLabel?.text = dictionary["Name"] as? String
        return cell
    }
    

    public override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let yourVC = self.storyboard?.instantiateViewController(withIdentifier: "locationDetails") as! RLLocationDetailViewController
        let dictionary = items[indexPath.row] as! NSDictionary
        yourVC.placeName = dictionary["Name"] as? String
        yourVC.placeImage = UIImage(named:(dictionary["ImageName"] as? String)!)
        yourVC.placeTags = dictionary["Tags"] as? Array
        yourVC.placeDescription = dictionary["Description"] as? String
        let lat = Double((dictionary["lat"] as? String)!)
        let lang = Double((dictionary["lang"] as? String)!)
        yourVC.placeLat = lat!
        yourVC.placeLang = lang!
        yourVC.placeLocation = CLLocationCoordinate2D(latitude: lat!, longitude: lang!)
        self.navigationController?.pushViewController(yourVC, animated: true)
    }

    // MARK: UICollectionViewDelegate

    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */

}
