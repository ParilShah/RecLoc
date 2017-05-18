//
//  RLResultViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 5/17/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Pulley
import DZNEmptyDataSet
import NVActivityIndicatorView

private let reuseIdentifier = "PlacesCell"

class RLResultViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource,DZNEmptyDataSetSource, DZNEmptyDataSetDelegate {
    var items = [Any]()
    public var locationString:String?
    public var category:Categories?
    var stringPlaceHolder: String?
    
    @IBOutlet var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        self.collectionView!.emptyDataSetSource = self
        self.collectionView!.emptyDataSetDelegate = self
        self.navigationItem.title = "Your Search Result"
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(image: UIImage.init(named: "navHome"), style:.plain, target: self, action: #selector(pressHome))
        self.stringPlaceHolder = "Searching locations for "+locationString!+" and "+(category?.categoryName)!
        fetchAllLocations()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func pressHome(){
        self.dismiss(animated: true, completion: nil)
    }
    
    func fetchAllLocations(){
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        let rlPlacesVM = RLPlacesVM.init(urlString: Constant.baseURL + "location/fetchLocations", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Location]
            self.stringPlaceHolder = (self.items.count == 0) ? "No Result Found. Right now, We are improving our collections. Please change your search criteria." : "Searching locations for "+self.locationString!+" and "+(self.category?.categoryName)!
            self.collectionView?.reloadData()
            activityIndicator.stopAnimating()
        })
        rlPlacesVM.fetchLocationsByCategoriesIdsandCountry(country: locationString!, category:category!)
    }

}

extension RLResultViewController {
    
    // MARK: UICollectionViewDataSource
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
    {
        return self.items.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
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
    
    public func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
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

extension RLResultViewController {
    
    // MARK: - DZNEmptyDataSetSource
    func title(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "RecLoc"
        let attributes = [
            NSFontAttributeName: UIFont.boldSystemFont(ofSize: 18.0)
        ]
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func description(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = stringPlaceHolder
        let paragraph = NSMutableParagraphStyle()
        paragraph.lineBreakMode = .byWordWrapping
        paragraph.alignment = .center
        
        let attributes = [
            NSFontAttributeName: UIFont.systemFont(ofSize: 14.0),
            NSParagraphStyleAttributeName: paragraph
        ]
        return NSAttributedString(string: text!, attributes: attributes)
    }
    /*
     func image(forEmptyDataSet scrollView: UIScrollView!) -> UIImage! {
     return UIImage(named: "placeholder_photo")
     }
     */
    func backgroundColor(forEmptyDataSet scrollView: UIScrollView!) -> UIColor! {
        return UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
    }
    
    
}
